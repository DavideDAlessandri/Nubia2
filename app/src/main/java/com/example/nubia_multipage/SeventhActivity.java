package com.example.nubia_multipage;

import android.content.SharedPreferences;
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

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);            //remember which display we are
        Integer port = sh.getInt("port", 0);
        String portString=port.toString();

        if(portString.equals("8081")){
            eyeR.setImageResource(eyeRight);                                                            //Set eye right on open activity
            eyeL.setImageResource(0);
        }else{
            eyeR.setImageResource(0);                                                            //Set eye right on open activity
            eyeL.setImageResource(eyeLeft);
            pageL=true;
        }


        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        new Thread(new SeventhActivity.Thread1()).start();
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
            new Thread(new SeventhActivity.Thread2("PGR07")).start();                      // send current page name to server
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
        }else if(message.equals("PGD02")) {
            finish();
        }else if(message.equals("PGD03")) {
            finish();
        }else if(message.equals("PGD04")) {
            finish();
        }else if(message.equals("PGD05")) {
            finish();
        }else if(message.equals("PGD06")) {
            finish();
        }else if(message.equals("PGD07")) {
            MyService.messageToActivity = "null";
            new Thread(new SeventhActivity.Thread1()).start();
        }else if(message.equals("PGD01")){
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
