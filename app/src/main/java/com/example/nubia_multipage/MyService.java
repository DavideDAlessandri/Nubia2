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

            if(level==40){
                if(!messageBatterySend){
                    new Thread(new MyService.Thread3("start charge")).start();
                    messageBatterySend=true;
                }
            }else if(level==80){
                if(messageBatterySend){
                    new Thread(new MyService.Thread3("stop charge")).start();
                    messageBatterySend=false;
                }
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

                    //On start device is charging, if level > 80 stop charge
                    if(level>80){
                        new Thread(new MyService.Thread3("stop charge")).start();
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

}
