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
    ImageView imageRun, imageTeach, imageHand, imageSettings, imageMonitor, imageAddOns;
    ImageView tryConnection, backButton;
    Boolean running=true;                                                                           //Thread 1 start/stop

    public static Boolean startStatus=false;                                                        //Stop activity when start another activity
    public static Boolean screenSaverOn=false;
    public static Boolean screenSaverIn=true;


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

        imageRun=findViewById(R.id.imageRun);
        imageTeach=findViewById(R.id.imageTeach);
        imageHand=findViewById(R.id.imageHand);
        imageSettings=findViewById(R.id.imageSettings);
        imageMonitor=findViewById(R.id.imageMonitor);
        imageAddOns=findViewById(R.id.imageAddOns);

        tryConnection=findViewById(R.id.tryConnection);
        tryConnection.setVisibility(View.GONE);                                                     //Connection symbol
        backButton=findViewById(R.id.backButton);
        backButton.setVisibility(View.GONE);                                                        // Back symbol

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        secondPage.setOnClickListener(new View.OnClickListener() {                                  //Change page buttons
            @Override
            public void onClick(View v) {

                Animation animation;                                                                //button animation
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_l);
                imageRun.startAnimation(animation);

                running=false;                                                                      //Stop TCp listener
                changeActivityTwo();                                                                //Change activity
            }
        });

        thirdPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation;                                                                //button animation
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_l);
                imageTeach.startAnimation(animation);

                running=false;                                                                      //Stop TCp listener
                changeActivityThree();
            }
        });

        fourthPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation;                                                                //button animation
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_r);
                imageSettings.startAnimation(animation);

                running=false;                                                                      //Stop TCp listener
                changeActivityFour();
            }
        });

        fifthPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation;                                                                //button animation
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_l);
                imageHand.startAnimation(animation);

                running=false;                                                                      //Stop TCp listener
                changeActivityFive();
            }
        });

        sixthPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation;                                                                //button animation
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_r);
                imageMonitor.startAnimation(animation);

                running=false;                                                                      //Stop TCp listener
                changeActivitySix();
            }
        });

        seventhPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation;                                                                //button animation
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_r);
                imageAddOns.startAnimation(animation);

                running=false;                                                                      //Stop TCp listener
                changeActivitySeven();
            }
        });

        if (MyService.connectStatus.equals(false)) {
            tryToConnect();
        }



    }

    @Override
    protected  void onResume(){                                                                     //When enter page:
        super.onResume();
        screenSaverOn=true;

        if (MyService.connectStatus.equals(true)){                                                  //If server connected (background service)

            MyService.messageToActivity="null";
            running=true;
            new Thread(new MainActivity.Thread2("PGR01")).start();                                  // send current page name to server
            new Thread(new MainActivity.Thread1()).start();                                         //Start thread 1

        }

        if(screenSaverIn){
            screenSaverIn=false;
            Handler handler = new Handler();
            handler. postDelayed(new Runnable() {
                public void run() {

                    if(screenSaverOn){
                        screenSaver();
                    }else{
                        screenSaverIn=true;
                    }
                }
            }, 30000); //10 seconds.                                                       //screensaver timer
        }

    }

    class Thread1 implements Runnable {                                                             //Server message reader
        @Override
        public void run() {

            if (!running) return;                                                                   //Stop running when exit page
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(MyService.messageToActivity.equals("PGD02")){                                //If message = "start" change to activity 2 run
                        MyService.messageToActivity="null";
                        running=false;
                        startStatus=true;                                                           //stop "old" activity
                        changeActivityTwo();

                        return;
                    }else if(MyService.messageToActivity.equals("PGD03")) {                         //teach
                        MyService.messageToActivity="null";
                        startStatus=true;
                        changeActivityThree();
                        return;
                    }else if(MyService.messageToActivity.equals("PGD05")) {                         //hand guide
                        MyService.messageToActivity="null";
                        startStatus=true;
                        changeActivityFive();
                        return;
                    }else if(MyService.messageToActivity.equals("PGD04")) {                         //settings
                        MyService.messageToActivity="null";
                        startStatus=true;
                        changeActivityFour();
                        return;
                    }else if(MyService.messageToActivity.equals("PGD06")) {                         //monitor
                        MyService.messageToActivity="null";
                        startStatus=true;
                        changeActivitySix();
                        return;
                    }else if(MyService.messageToActivity.equals("PGD07")) {                         //add-ons
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

    class Thread2 implements Runnable {                                                             //Phone message reader / sender
        private String message;
        Thread2(String message) {
            this.message = message;
        }
        @Override
        public void run() {
            MyService.output.write(message);
            MyService.output.flush();
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

    private void screenSaver(){
        Intent Intent = new Intent(this,Screensaver.class);
        startActivity(Intent);
    }


    private void startService(){                                                                    //Start background service
        startService(new Intent(this, MyService.class));
    }

    private void tryToConnect(){
        tryConnection.setVisibility(View.VISIBLE);                                                  //try connection symbol

        Handler handler = new Handler();                                                    //Start thread 1 after 1 second
        handler. postDelayed(new Runnable() {
            public void run() {

                if (MyService.connectStatus.equals(true)) {                                 //If server connected:

                    tryConnection.setVisibility(View.GONE);
                    running = true;                                                         //Allow thread 1 to run
                    MyService.messageToActivity = "null";                                   //Set incoming message "null"
                    new Thread(new MainActivity.Thread1()).start();                         //Start thread 1 (read incoming message)

                }else{
                    startService();                                                                 //Start background service (TCP connection)
                    tryToConnect();
                }
            }
        }, 10000); //10 seconds.                                                              //try to connect every 10 second

    }

    @Override
    protected void onStop() {

        super.onStop();
        running=false;                                                                              //When exit page stop thread 1
    }

    @Override
    public void onBackPressed() {
        backButton.setVisibility(View.VISIBLE);

        Handler handler = new Handler();                                                            //Hide back button after 5 second
        handler. postDelayed(new Runnable() {
            public void run() {
                backButton.setVisibility(View.GONE);
            }
        }, 5000); //10 seconds.
    }


}

