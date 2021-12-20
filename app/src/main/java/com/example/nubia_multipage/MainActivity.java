package com.example.nubia_multipage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button secondPage;
    Button thirdPage;
    Button fourthPage;
    Button fifthPage;
    Button sixthPage;
    Button seventhPage;
    Button connectButton;
    ImageView imageRun, imageTeach, imageHand, imageSettings, imageMonitor, imageAddOns;
    Boolean running=true;                                                                           //Thread 1 start/stop
    public static Boolean startStatus=false;                                                        //Stop activity when start another activity


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
        sixthPage=findViewById(R.id.MonitorPage);
        seventhPage=findViewById(R.id.AddOnsPage);
        connectButton=findViewById(R.id.connectButton);

        imageRun=findViewById(R.id.imageRun);
        imageTeach=findViewById(R.id.imageTeach);
        imageHand=findViewById(R.id.imageHand);
        imageSettings=findViewById(R.id.imageSettings);
        imageMonitor=findViewById(R.id.imageMonitor);
        imageAddOns=findViewById(R.id.imageAddOns);


        secondPage.setOnClickListener(new View.OnClickListener() {                                  //Change page buttons
            @Override
            public void onClick(View v) {

                Animation animation;                                                                //button animation
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_r);
                imageRun.startAnimation(animation);

                changeActivityTwo();                                                                //Change activity
            }
        });

        thirdPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation;                                                                //button animation
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_r);
                imageTeach.startAnimation(animation);

                changeActivityThree();
            }
        });

        fourthPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation;                                                                //button animation
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_l);
                imageSettings.startAnimation(animation);

                changeActivityFour();
            }
        });

        fifthPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation;                                                                //button animation
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_r);
                imageHand.startAnimation(animation);

                changeActivityFive();
            }
        });

        sixthPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation;                                                                //button animation
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_l);
                imageMonitor.startAnimation(animation);

                changeActivitySix();
            }
        });

        seventhPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation;                                                                //button animation
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_l);
                imageAddOns.startAnimation(animation);

                changeActivitySeven();
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

                    if(MyService.messageToActivity.equals("run")){                                  //If message = "start" change to activity 2
                        MyService.messageToActivity="null";
                        startStatus=true;                                                           //stop "old" activity
                        changeActivityTwo();
                        return;
                    }else if(MyService.messageToActivity.equals("teach")) {
                        MyService.messageToActivity="null";
                        startStatus=true;
                        changeActivityThree();
                        return;
                    }else if(MyService.messageToActivity.equals("hand")) {
                        MyService.messageToActivity="null";
                        startStatus=true;
                        changeActivityFive();
                        return;
                    }else if(MyService.messageToActivity.equals("settings")) {
                        MyService.messageToActivity="null";
                        startStatus=true;
                        changeActivityFour();
                        return;
                    }else if(MyService.messageToActivity.equals("monitor")) {
                        MyService.messageToActivity="null";
                        startStatus=true;
                        changeActivitySix();
                        return;
                    }else if(MyService.messageToActivity.equals("addons")) {
                        MyService.messageToActivity="null";
                        startStatus=true;
                        changeActivitySeven();
                        return;
                    }else{
                        new Thread(new MainActivity.Thread1()).start();                             //Else restart thread
                    }

                }
            });

        }
    }


    private void changeActivityTwo(){                                                               //Change activity
        new Thread(new MainActivity.Thread1()).start();
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);                     //animation out
    }

    private void changeActivityThree(){
        new Thread(new MainActivity.Thread1()).start();
        Intent intent = new Intent(this,ThirdActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);                    //start animation
    }

    private void changeActivityFour(){
        new Thread(new MainActivity.Thread1()).start();
        Intent intent = new Intent(this,FourthActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);                    //start animation
    }

    private void changeActivityFive(){
        new Thread(new MainActivity.Thread1()).start();
        Intent intent = new Intent(this,FifthActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);                    //start animation
    }

    private void changeActivitySix(){
        new Thread(new MainActivity.Thread1()).start();
        Intent Intent = new Intent(this,SixthActivity.class);
        startActivity(Intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);                    //start animation
    }

    private void changeActivitySeven(){
        new Thread(new MainActivity.Thread1()).start();
        Intent Intent = new Intent(this,SeventhActivity.class);
        startActivity(Intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);                    //start animation
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

