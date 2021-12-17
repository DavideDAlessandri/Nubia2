package com.example.nubia_multipage;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.second_ly_run);

        color= findViewById(R.id.color);
        menu=findViewById(R.id.menu);
        label=findViewById(R.id.label);

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
        changeColor("start");
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
        int greenMenu = getResources().getIdentifier("@drawable/runtime_bg",null,this.getPackageName());
        int greenLabel= getResources().getIdentifier("@drawable/runtime_green",null,this.getPackageName());
        int redLabel= getResources().getIdentifier("@drawable/runtime_red",null,this.getPackageName());
        int yellowLabel= getResources().getIdentifier("@drawable/runtime_yellow",null,this.getPackageName());

        String screenMessage=message.substring(2);
        String colorMessage=message.substring(0,2);

        if(message.equals("null")){
            new Thread(new SecondActivity.Thread1()).start();
        }else{
            if(colorMessage.equals("R1")){
                color.setImageResource(redColor);
                menu.setImageResource(0);
                label.setImageResource(redLabel);
                txtMarquee.setText(screenMessage + " " + screenMessage + " " + screenMessage + " ");
                txtMarquee.setSelected(true);

                Animation animation = new AlphaAnimation(1, 0); //to change visibility from visible to invisible
                animation.setDuration(1000); //1 second duration for each animation cycle
                animation.setInterpolator(new LinearInterpolator());
                animation.setRepeatCount(Animation.INFINITE); //repeating indefinitely
                animation.setRepeatMode(Animation.REVERSE); //animation will start from end point once ended.
                color.startAnimation(animation); //to start animation

            }
            if(colorMessage.equals("R2")) {
                color.setImageResource(redColor);
                menu.setImageResource(0);
                label.setImageResource(redLabel);
                txtMarquee.setText(screenMessage + " " + screenMessage + " " + screenMessage + " ");
                txtMarquee.setSelected(true);
                color.clearAnimation();
            }

            if(colorMessage.equals("G1")){
                color.setImageResource(greenColor);
                menu.setImageResource(greenMenu);
                label.setImageResource(greenLabel);
                txtMarquee.setText(screenMessage + " " + screenMessage + " " + screenMessage + " ");
                txtMarquee.setSelected(true);
                color.clearAnimation();
            }

            if(colorMessage.equals("Y1")){
                color.setImageResource(yellowColor);
                menu.setImageResource(0);
                label.setImageResource(yellowLabel);
                txtMarquee.setText(screenMessage + " " + screenMessage + " " + screenMessage + " ");
                txtMarquee.setSelected(true);
                color.clearAnimation();
            }
            if(message.equals("start")){
                color.setImageResource(greenColor);
                menu.setImageResource(greenMenu);
                label.setImageResource(greenLabel);
                txtMarquee.setText("Message Message Message");
                txtMarquee.setSelected(true);
                color.clearAnimation();
            }
            if(message.equals("stop")){
                finish();
            }

            MyService.messageToActivity="null";
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

