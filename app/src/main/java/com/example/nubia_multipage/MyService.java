package com.example.nubia_multipage;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyService extends Service {

    Thread Thread1 = null;
    //String SERVER_IP = "192.168.100.2";       //IP PC Davide
    String SERVER_IP = "192.168.100.100";       //IP Robot
    int port;


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

    @Override
    public void onCreate() {
    }

    @Override
    public void onStart(Intent intent, int startId) {

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);            //remember which display we are
        port = sh.getInt("port", 0);

        Thread1 = new Thread(new MyService.Thread1());
        Thread1.start();
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



}
