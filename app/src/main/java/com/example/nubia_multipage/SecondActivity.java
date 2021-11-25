package com.example.nubia_multipage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
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
    FrameLayout layout;
    Boolean running=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.second_layout);

        color= findViewById(R.id.color);
        gif= findViewById(R.id.gif);

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

        running=true;
        changeColor("blue");
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



    private void changeColor(String message){

        TextView txtMarquee;                                                                        //Sliding text
        txtMarquee = findViewById(R.id.marqueeText);

                                                                                                    //Import images
        int redColor = getResources().getIdentifier("@drawable/statusbar_red",null,this.getPackageName());
        int greenColor = getResources().getIdentifier("@drawable/statusbar_green",null,this.getPackageName());
        int yellowColor = getResources().getIdentifier("@drawable/statusbar_yellow",null,this.getPackageName());
        int blueColor = getResources().getIdentifier("@drawable/statusbar_blue",null,this.getPackageName());
        int gifRobot = getResources().getIdentifier("@drawable/gif_robot",null,this.getPackageName());
        int gifEye = getResources().getIdentifier("@drawable/gif_eye",null,this.getPackageName());

                                                                                                    //Change image if message is "red", "yellow" .....
        if(message.equals("red")){
            color.setImageResource(redColor);
            gif.setImageResource(gifRobot);
            txtMarquee.setText("Errore Errore Errore");
            txtMarquee.setSelected(true);

            Animation animation = new AlphaAnimation(1, 0); //to change visibility from visible to invisible
            animation.setDuration(1000); //1 second duration for each animation cycle
            animation.setInterpolator(new LinearInterpolator());
            animation.setRepeatCount(Animation.INFINITE); //repeating indefinitely
            animation.setRepeatMode(Animation.REVERSE); //animation will start from end point once ended.
            color.startAnimation(animation); //to start animation

        }

        if(message.equals("green")){
            color.setImageResource(greenColor);
            gif.setImageResource(0);
            txtMarquee.setText("Verde Verde Verde");
            txtMarquee.setSelected(true);
            color.clearAnimation();
        }

        if(message.equals("yellow")){
            color.setImageResource(yellowColor);
            gif.setImageResource(gifEye);
            txtMarquee.setText("Giallo Giallo Giallo");
            txtMarquee.setSelected(true);
            color.clearAnimation();
        }
        if(message.equals("blue")){
            color.setImageResource(blueColor);
            gif.setImageResource(0);
            txtMarquee.setText("Test test test test");
            txtMarquee.setSelected(true);
            color.clearAnimation();
        }
        if(message.equals("back")){
            finish();
        }

        MyService.messageToActivity="null";
        new Thread(new SecondActivity.Thread1()).start();



    }

    @Override
    protected void onStop() {

        super.onStop();
        running=false;
    }


    }

