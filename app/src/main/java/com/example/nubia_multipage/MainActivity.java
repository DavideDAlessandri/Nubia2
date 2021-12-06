package com.example.nubia_multipage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    TextView secondPage;
    TextView thirdPage;
    TextView fourthPage;
    TextView fifthPage;
    Button connectButton;
    Boolean running=true;                                                                           //Thread 1 start/stop

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        secondPage=findViewById(R.id.RunPage);
        thirdPage = findViewById(R.id.TeachPage);
        fourthPage=findViewById(R.id.SettingsPage);
        fifthPage=findViewById(R.id.HandPage);
        connectButton=findViewById(R.id.connectButton);


        secondPage.setOnClickListener(new View.OnClickListener() {                                  //Change page buttons
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


        connectButton.setVisibility(View.VISIBLE);
        connectButton.setOnClickListener(new View.OnClickListener() {                               //Connect button
            @Override
            public void onClick(View v) {
                startService();                                                                     //Start background service (TCP connection)

                Handler handler = new Handler();                                                    //Start thread 1 after 1 second
                handler. postDelayed(new Runnable() {
                    public void run() {

                        if (MyService.connectStatus.equals(true)) {                                 //If server connected:

                            connectButton.setVisibility(View.GONE);                                 //Hide button
                            running = true;                                                         //Allow thread 1 to run
                            MyService.messageToActivity = "null";                                   //Set incoming message "null"
                            new Thread(new MainActivity.Thread1()).start();                         //Start thread 1 (read incoming message)

                        }
                        }
                }, 500); //0.5 seconds.

            }
        });

    }

    @Override
    protected  void onResume(){                                                                     //When enter page:
        super.onResume();

        if (MyService.connectStatus.equals(true)){                                                  //If server connected (background service)

            MyService.messageToActivity="null";
            running=true;
            new Thread(new MainActivity.Thread1()).start();                                         //Start thread 1
        }
    }

    class Thread1 implements Runnable {                                                             //Server message reader
        @Override
        public void run() {

            if (!running) return;                                                                   //Stop running when exit page
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(MyService.messageToActivity.equals("go")){                                   //If message = "go" change to activity 2
                        running=false;
                        MyService.messageToActivity="null";
                        changeActivityTwo();
                        return;
                    }else{
                        new Thread(new MainActivity.Thread1()).start();                             //Else restart thread
                    }

                }
            });

        }
    }


    private void changeActivityTwo(){                                                               //Change activity
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

    private void changeActivityThree(){
        Intent intent = new Intent(this,ThirdActivity.class);
        startActivity(intent);
    }

    private void changeActivityFour(){
        Intent intent = new Intent(this,FourthActivity.class);
        startActivity(intent);
    }

    private void changeActivityFive(){
        Intent intent = new Intent(this,FifthActivity.class);
        startActivity(intent);
    }


    private void startService(){                                                                    //Start background service
        startService(new Intent(this, MyService.class));
    }


    @Override
    protected void onStop() {

        super.onStop();
        running=false;                                                                              //When exit page stop thread 1
    }

}

