package com.example.nubia_multipage;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyService extends Service {


    Thread Thread1 = null;
    String SERVER_IP = "192.168.100.100";       //IP Robot
    int port;

    int level;
    Boolean messageBatterySend=true;
    Boolean backIfCharging=true;
    public static Integer currentPage;

    public static PrintWriter output;
    public static BufferedReader input;
    public  static String messageToActivity;
    public static Boolean connectStatus=false;

    //Memory for seekbar page6 monitor:
    public static Integer limit1=0;
    public static Integer limit2=0;
    public static Integer limit3=0;
    public static Integer limit4=0;
    public static Integer limit5=0;
    public static Integer limit6=0;

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {                          //read battery level and send message to PLC
        @Override
        public void onReceive(Context context, Intent intent) {

            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);

            if(level==20){
                if(!messageBatterySend){
                    new Thread(new MyService.Thread3("BTRstart")).start();
                    messageBatterySend=true;
                }
            }else if(level==90){
                if(messageBatterySend){
                    new Thread(new MyService.Thread3("BTRstop")).start();
                    messageBatterySend=false;
                }
            }

            //See if in charge or not
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, ifilter);

            int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;

            if(isCharging && connectStatus){

                Handler handler = new Handler();        //wait charging animation
                handler. postDelayed(new Runnable() {
                    public void run() {

                        if(backIfCharging){
                            goBack();
                            backIfCharging=false;
                        }

                    }
                }, 500);


            }else{
                Handler handler = new Handler();        //wait charging animation
                handler. postDelayed(new Runnable() {
                    public void run() {

                        backIfCharging=true;

                    }
                }, 510);


            }
        }
    };

    @Override
    public void onCreate() {

        //battery status register
        this.registerReceiver(this.mBatInfoReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }

    @Override
    public void onStart(Intent intent, int startId) {

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);            //remember which display we are
        port = sh.getInt("port", 0);

        Thread1 = new Thread(new MyService.Thread1());
        Thread1.start();


        Handler handler = new Handler();        //wait until device is connected to the server
        handler. postDelayed(new Runnable() {
            public void run() {

                if(connectStatus){

                    //On start device is charging, if level > 90 stop charge
                    if(level>90){
                        new Thread(new MyService.Thread3("BTRstop")).start();
                        messageBatterySend=false;
                    }
                }

            }
        }, 10000);


    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    class Thread1 implements Runnable {                                //Server connection
        public void run() {
            Socket socket;
            try {
                socket = new Socket(SERVER_IP, port);           //connection server ip / port
                output = new PrintWriter(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                connectStatus=true;
                new Thread(new MyService.Thread2()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Thread2 implements Runnable {                             //Server message reader
        @Override
        public void run() {
            while (true) {
                try {
                    final String message = input.readLine();

                    messageToActivity=message;

                    if(messageToActivity.equals("back")){
                        goBack();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Thread3 implements Runnable {                                                             //Phone message reader / sender
        private String message;
        Thread3(String message) {
            this.message = message;
        }
        @Override
        public void run() {
            MyService.output.write(message);
            MyService.output.flush();
        }
    }


    private void goBack(){

        switch (currentPage){
            case 1:
                MainActivity.fa1.finish();
                Intent intent1 = new Intent(this,MainActivity.class);
                startActivity(intent1);
                break;
            case 2:
                SecondActivity.fa2.finish();
                Intent intent2 = new Intent(this,SecondActivity.class);
                startActivity(intent2);
                break;
            case 3:
                //to-do
                break;
            case 4:
                FourthActivity.fa4.finish();
                Intent intent4 = new Intent(this,FourthActivity.class);
                startActivity(intent4);
                break;
            case 5:
                FifthActivity.fa5.finish();
                Intent intent5 = new Intent(this,FifthActivity.class);
                startActivity(intent5);
                break;
            case 6:
                SixthActivity.fa6.finish();
                Intent intent6 = new Intent(this,SixthActivity.class);
                startActivity(intent6);
                break;
            case 7:
                SeventhActivity.fa7.finish();
                Intent intent7 = new Intent(this,SeventhActivity.class);
                startActivity(intent7);
                break;
            case 8:
                EightActivity.fa8.finish();
                Intent intent8 = new Intent(this,EightActivity.class);
                startActivity(intent8);
                break;

        }

    }
}
