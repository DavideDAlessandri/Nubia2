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

    private Button backPage;
    private Button thirdPage;
    ImageView color;

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
        thirdPage=findViewById(R.id.thirdPageTwo);
        backPage=findViewById(R.id.backPage);


        //communication
        Thread1 = new Thread(new SecondActivity.Thread1());
        Thread1.start();


        //Page changing
        thirdPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityThree();
            }
        });

        backPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

        int redColor = getResources().getIdentifier("@drawable/red",null,this.getPackageName());
        int greenColor = getResources().getIdentifier("@drawable/green",null,this.getPackageName());
        int yellowColor = getResources().getIdentifier("@drawable/yellow",null,this.getPackageName());

        if(message.equals("red")){
            color.setImageResource(redColor);
        }

        if(message.equals("green")){
            color.setImageResource(greenColor);
        }

        if(message.equals("yellow")){
            color.setImageResource(yellowColor);
        }
    }

    private void changeActivityThree(){                                      //Click change page
        Intent intent = new Intent(this,ThirdActivity.class);
        startActivity(intent);
    }

    }

