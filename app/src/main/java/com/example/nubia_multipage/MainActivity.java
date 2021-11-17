package com.example.nubia_multipage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {

    Button secondPage;
    Button thirdPage;
    Button fourthPage;
    Button fifthPage;
    Thread Thread1 = null;
    String SERVER_IP = "192.168.100.100";
    int SERVER_PORT = 8080;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        secondPage=findViewById(R.id.secondPage);
        thirdPage = findViewById(R.id.thirdPage);
        fourthPage=findViewById(R.id.fourthPage);
        fifthPage=findViewById(R.id.fifthPage);

        secondPage.setPaintFlags(secondPage.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);       //underline button
        thirdPage.setPaintFlags(thirdPage.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        fourthPage.setPaintFlags(fourthPage.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        fifthPage.setPaintFlags(fifthPage.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        secondPage.setOnClickListener(new View.OnClickListener() {      //Click change page
            @Override
            public void onClick(View v) {
                changeActivityTwo();
            }
        });

        thirdPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityThree();
            }
        });

        fourthPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityFour();
            }
        });

        fifthPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityFive();
            }
        });

        //communication
        Thread1 = new Thread(new MainActivity.Thread1());
        Thread1.start();

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
                new Thread(new MainActivity.Thread2()).start();
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
                                if(message.equals("go")){
                                    changeActivityTwo();
                                }
                            }
                        });
                    } else {
                        Thread1 = new Thread(new MainActivity.Thread1());
                        Thread1.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void changeActivityTwo(){                                        //Click change page
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

    private void changeActivityThree(){                                      //Click change page
        Intent intent = new Intent(this,ThirdActivity.class);
        startActivity(intent);
    }

    private void changeActivityFour(){                                       //Click change page
        Intent intent = new Intent(this,FourthActivity.class);
        startActivity(intent);
    }

    private void changeActivityFive(){
        Intent intent = new Intent(this,FifthActivity.class);
        startActivity(intent);
    }

}