package com.example.nubia_multipage;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SeventhActivity extends AppCompatActivity {

    ImageView eyeR, eyeL;
    ImageView gifR, gifL;
    View layout;
    MediaPlayer mp;
    Boolean running=true;
    Boolean pageL=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.seventh_ly_add_ons);

        eyeR=findViewById(R.id.eyeR);
        eyeL=findViewById(R.id.eyeL);
        gifR=findViewById(R.id.gifR);
        gifL=findViewById(R.id.gifL);
        layout=findViewById(R.id.layout7);
        mp = MediaPlayer.create(this, R.raw.sound_1_retro);

        int eyeLeft = getResources().getIdentifier("@drawable/eye_l",null,this.getPackageName());
        int eyeRight = getResources().getIdentifier("@drawable/eye_r",null,this.getPackageName());

        eyeR.setImageResource(eyeRight);                                                            //Set eye right on open activity
        eyeL.setImageResource(0);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!pageL){                                                                         //If tap on screen inverse eye position
                    eyeR.setImageResource(0);
                    eyeL.setImageResource(eyeLeft);

                    //animation pulse
                    //Animation connectingAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_scale_animation);
                    //eyeL.startAnimation(connectingAnimation);

                    pageL=true;
                }else{
                    eyeR.setImageResource(eyeRight);
                    eyeL.setImageResource(0);

                    //animation pulse
                    //Animation connectingAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_scale_animation);
                    //eyeR.startAnimation(connectingAnimation);

                    pageL=false;
                }
                new Thread(new SeventhActivity.Thread1()).start();

            }
        });

    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);                     //animation out
    }

    @Override
    protected  void onResume(){
        super.onResume();

        MainActivity.startStatus=false;                                                             //reset start status (stop when change activity)
        running=true;
        MyService.messageToActivity="null";

        if(MyService.connectStatus){                                                                //if tcp connected
            new Thread(new SeventhActivity.Thread2("AddOnsActivity")).start();                      // send current page name to server
            new Thread(new SeventhActivity.Thread1()).start();
        }


    }

    class Thread1 implements Runnable {                                                             //Server message reader
        @Override
        public void run() {

            if (!running) return;                                                                   //Exit thread
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    receiveValue(MyService.messageToActivity);

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

    private  void receiveValue(String message){

        int eyeLeft = getResources().getIdentifier("@drawable/eye_l",null,this.getPackageName());
        int eyeRight = getResources().getIdentifier("@drawable/eye_r",null,this.getPackageName());
        int eyeSLeft = getResources().getIdentifier("@drawable/eye_s_l",null,this.getPackageName());
        int eyeSRight = getResources().getIdentifier("@drawable/eye_s_r",null,this.getPackageName());
        int gifL =  getResources().getIdentifier("@drawable/gif_l",null,this.getPackageName());
        int gifR =  getResources().getIdentifier("@drawable/gif_r",null,this.getPackageName());


        if(message.equals("null")) {
            new Thread(new SeventhActivity.Thread1()).start();
        }else if(message.equals("run")) {
            finish();
        }else if(message.equals("teach")) {
            finish();
        }else if(message.equals("hand")) {
            finish();
        }else if(message.equals("settings")) {
            finish();
        }else if(message.equals("monitor")) {
            finish();
        }else if(message.equals("addons")) {

            pageL=true;                                                                             //if pageLR = true => display R = display L
            eyeR.setImageResource(0);                                                               //Change eye position
            eyeL.setImageResource(eyeLeft);

            MyService.messageToActivity = "null";
            new Thread(new SeventhActivity.Thread1()).start();

        }else if(message.equals("stop")) {
            finish();
        }else{
            if(pageL){                                                                              //change eye Left or Right

                if(message.equals("1")){
                    eyeL.setImageResource(eyeSLeft);
                }
                if(message.equals("2")){
                    eyeL.setImageResource(eyeLeft);
                }
                if(message.equals("3")){
                    mp.start();
                }
            }else{
                if(message.equals("1")){
                    eyeR.setImageResource(eyeSRight);
                }
                if(message.equals("2")){
                    eyeR.setImageResource(eyeRight);
                }
                if(message.equals("3")){
                    mp.start();
                }
            }


            MyService.messageToActivity = "null";
            new Thread(new SeventhActivity.Thread1()).start();
        }
    }



}
