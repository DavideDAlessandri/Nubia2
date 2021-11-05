package com.example.nubia_multipage;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SecondActivity extends AppCompatActivity {

    ImageView color;
    ImageView gif;

    Thread Thread1 = null;
    String SERVER_IP = "192.168.1.2";
    int SERVER_PORT = 8080;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.second_layout);

        color=(ImageView) findViewById(R.id.color);
        gif=(ImageView) findViewById(R.id.gif);

        //communication
        Thread1 = new Thread(new SecondActivity.Thread1());
        Thread1.start();

        changeColor("blue");

        }

    private PrintWriter output;
    private BufferedReader input;
    class Thread1 implements Runnable {                                //Server connection
        public void run() {
            Socket socket;
            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);           //connection server ip / port
                output = new PrintWriter(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                new Thread(new SecondActivity.Thread2()).start();
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
                    if (message!= null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                changeColor(message);
                            }
                        });
                    } else {
                        Thread1 = new Thread(new SecondActivity.Thread1());
                        Thread1.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void changeColor(String message){

        TextView txtMarquee;
        txtMarquee = (TextView) findViewById(R.id.marqueeText);

        int redColor = getResources().getIdentifier("@drawable/statusbar_red",null,this.getPackageName());
        int greenColor = getResources().getIdentifier("@drawable/statusbar_green",null,this.getPackageName());
        int yellowColor = getResources().getIdentifier("@drawable/statusbar_yellow",null,this.getPackageName());
        int blueColor = getResources().getIdentifier("@drawable/statusbar_blue",null,this.getPackageName());
        int gifRobot = getResources().getIdentifier("@drawable/gif_robot",null,this.getPackageName());
        int gifEye = getResources().getIdentifier("@drawable/gif_eye",null,this.getPackageName());

        if(message.equals("red")){
            color.setImageResource(redColor);
            gif.setImageResource(gifRobot);
            txtMarquee.setText("Errore Errore Errore");
            txtMarquee.setSelected(true);
        }

        if(message.equals("green")){
            color.setImageResource(greenColor);
            gif.setImageResource(0);
            txtMarquee.setText("Verde Verde Verde");
            txtMarquee.setSelected(true);
        }

        if(message.equals("yellow")){
            color.setImageResource(yellowColor);
            gif.setImageResource(gifEye);
            txtMarquee.setText("Giallo Giallo Giallo");
            txtMarquee.setSelected(true);
        }
        if(message.equals("blue")){
            color.setImageResource(blueColor);
            gif.setImageResource(0);
            txtMarquee.setText("Test test test test");
            txtMarquee.setSelected(true);
        }
        if(message.equals("back")){
            finish();
        }
    }

    private void changeActivityThree(){                                      //Click change page
        Intent intent = new Intent(this,ThirdActivity.class);
        startActivity(intent);
    }

    }

