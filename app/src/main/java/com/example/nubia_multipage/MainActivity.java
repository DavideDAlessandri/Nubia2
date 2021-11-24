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

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {

    Button secondPage;
    Button thirdPage;
    Button fourthPage;
    Button fifthPage;
    Button connectButton;
    Boolean running=true;


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
        connectButton=findViewById(R.id.connectButton);

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



        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();

                Handler handler = new Handler();
                handler. postDelayed(new Runnable() {
                    public void run() {

                        running=true;
                        MyService.messageToActivity="null";
                        new Thread(new MainActivity.Thread1()).start();

                    }
                }, 1000); //1 seconds.

            }
        });


    }

    @Override
    protected  void onResume(){
        super.onResume();

        if (MyService.connectStatus.equals(true)){

            MyService.messageToActivity="null";
            running=true;
            new Thread(new MainActivity.Thread1()).start();
        }
    }

    class Thread1 implements Runnable {                             //Server message reader
        @Override
        public void run() {

            if (!running) return;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(MyService.messageToActivity.equals("go")){
                        running=false;
                        MyService.messageToActivity="null";
                        changeActivityTwo();
                        return;
                    }else{
                        new Thread(new MainActivity.Thread1()).start();
                    }

                }
            });

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

    private void startService(){
        startService(new Intent(this, MyService.class));
    }



    @Override
    protected void onStop() {

        super.onStop();
        running=false;
    }

}

