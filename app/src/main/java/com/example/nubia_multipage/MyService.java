package com.example.nubia_multipage;

import android.app.Service;
import android.content.Intent;
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
    int SERVER_PORT = 8080;                     //8081


    public static PrintWriter output;
    public static BufferedReader input;
    public  static String messageToActivity;
    public static Boolean connectStatus=false;


    @Override
    public void onCreate() {
    }

    @Override
    public void onStart(Intent intent, int startId) {
        //do something
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
                socket = new Socket(SERVER_IP, SERVER_PORT);           //connection server ip / port
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
