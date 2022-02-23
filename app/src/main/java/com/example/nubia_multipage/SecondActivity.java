package com.example.nubia_multipage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    ImageView color, menu, label;
    FrameLayout layout;
    Boolean running=true;
    Boolean menuStatus=true;        //if menu status true => change color, if false => change numbers
    TextView step1, step2, etf;
    public static Activity fa2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.second_ly_run);

        fa2=this; //assign name activity 2

        color= findViewById(R.id.color);
        menu=findViewById(R.id.menu);
        label=findViewById(R.id.label);

        step1 =findViewById(R.id.step1);
        step2= findViewById(R.id.step2);
        etf=findViewById(R.id.etf);

        layout=findViewById(R.id.layout2);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


        }

    @Override
    protected  void onResume(){
        super.onResume();

        MyService.currentPage=2;

        MainActivity.screenSaverOn=false;
        MainActivity.startStatus=false;                                                             //reset start status (stop when change activity)
        running=true;
        changeColor("start");

       if(MyService.connectStatus){                                                                //If tcp connected
           new Thread(new SecondActivity.Thread2("PGR02")).start();                                // send current page name to server
        }
    }


    class Thread1 implements Runnable {
        @Override
        public void run() {

            if (!running) return;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    changeColor(MyService.messageToActivity);

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


    private void changeColor(String message){

        TextView txtMarquee;                                                                        //Sliding text
        txtMarquee = findViewById(R.id.marqueeText);

                                                                                                    //Import images
        int redColor = getResources().getIdentifier("@drawable/statusbar_red",null,this.getPackageName());
        int greenColor = getResources().getIdentifier("@drawable/statusbar_green",null,this.getPackageName());
        int yellowColor = getResources().getIdentifier("@drawable/statusbar_yellow",null,this.getPackageName());
        int greenMenu = getResources().getIdentifier("@drawable/runtime_bg",null,this.getPackageName());
        int greenLabel= getResources().getIdentifier("@drawable/runtime_green",null,this.getPackageName());
        int redLabel= getResources().getIdentifier("@drawable/runtime_red",null,this.getPackageName());
        int yellowLabel= getResources().getIdentifier("@drawable/runtime_yellow",null,this.getPackageName());

        if(MainActivity.startStatus) {                                                               //if another activity is called stop this activity
            finish();
        }

        String referenceMessage=message.substring(0,3);                                             //Identify message reference

        if(message.equals("PGD02")) {
            MyService.messageToActivity="null";
            new Thread(new SecondActivity.Thread1()).start();
        }else if(message.equals("PGD03")) {
            finish();
        }else if(message.equals("PGD04")) {
            finish();
        }else if(message.equals("PGD05")) {
            finish();
        }else if(message.equals("PGD06")) {
            finish();
        }else if(message.equals("PGD07")) {
            finish();
        }else if(message.equals("null")) {
            new Thread(new SecondActivity.Thread1()).start();
        }else if(referenceMessage.equals("TXT")){

            String screenMessage = message.substring(5);                                              //Subtract initial value of message to find color and option (Ex: G1)
            String colorMessage = message.substring(3, 5);

            if (colorMessage.equals("r2")) {
                color.setImageResource(redColor);
                menu.setImageResource(0);
                label.setImageResource(redLabel);
                txtMarquee.setText(screenMessage + " " + screenMessage + " " + screenMessage + " ");
                txtMarquee.setSelected(true);

                step1.setText(null);                                                                    //remove menu values
                step2.setText(null);
                etf.setText(null);

                Animation animation = new AlphaAnimation(1, 0); //to change visibility from visible to invisible
                animation.setDuration(1000); //1 second duration for each animation cycle
                animation.setInterpolator(new LinearInterpolator());
                animation.setRepeatCount(Animation.INFINITE); //repeating indefinitely
                animation.setRepeatMode(Animation.REVERSE); //animation will start from end point once ended.
                color.startAnimation(animation); //to start animation

            }
            if (colorMessage.equals("r1")) {
                color.setImageResource(redColor);
                menu.setImageResource(0);
                label.setImageResource(redLabel);
                txtMarquee.setText(screenMessage + " " + screenMessage + " " + screenMessage + " ");
                txtMarquee.setSelected(true);
                color.clearAnimation();
                step1.setText(null);                                                                    //remove menu values
                step2.setText(null);
                etf.setText(null);
            }

            if (colorMessage.equals("g1")) {
                color.setImageResource(greenColor);
                menu.setImageResource(greenMenu);
                label.setImageResource(greenLabel);
                txtMarquee.setText(screenMessage + " " + screenMessage + " " + screenMessage + " ");
                txtMarquee.setSelected(true);
                color.clearAnimation();
            }

            if (colorMessage.equals("y1")) {
                color.setImageResource(yellowColor);
                menu.setImageResource(0);
                label.setImageResource(yellowLabel);
                txtMarquee.setText(screenMessage + " " + screenMessage + " " + screenMessage + " ");
                txtMarquee.setSelected(true);
                color.clearAnimation();
                step1.setText(null);                                                                    //remove menu values
                step2.setText(null);
                etf.setText(null);
            }

            MyService.messageToActivity = "null";
            new Thread(new SecondActivity.Thread1()).start();


        }else if(referenceMessage.equals("INF")){

            String stringMessage=message.substring(3);
            String messageStep1 = stringMessage.substring(0, 2);                               //display menu info
            String messageStep2 = stringMessage.substring(2, 4);
            String messageEtf = stringMessage.substring(4);

            step1.setText(messageStep1);
            step2.setText(messageStep2);
            etf.setText(messageEtf);

            MyService.messageToActivity = "null";
            new Thread(new SecondActivity.Thread1()).start();

        }else if(referenceMessage.equals("IMG")){

            //to do
            MyService.messageToActivity = "null";
            new Thread(new SecondActivity.Thread1()).start();

        }else{

            if (message.equals("start")) {
                color.setImageResource(greenColor);
                menu.setImageResource(greenMenu);
                label.setImageResource(greenLabel);
                txtMarquee.setText("Message Message Message");
                txtMarquee.setSelected(true);
                color.clearAnimation();
            }
            if (message.equals("PGD01")) {
                finish();
            }

            MyService.messageToActivity = "null";
            new Thread(new SecondActivity.Thread1()).start();

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        running=false;
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);                     //animation out
    }

    }

