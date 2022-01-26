package com.example.nubia_multipage;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class FourthActivity extends AppCompatActivity {

    Boolean running=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.fourth_ly_settings);



    }

    @Override
    protected  void onResume(){
        super.onResume();

        MainActivity.startStatus=false;                                                             //reset start status (stop when change activity)
        running=true;
        MyService.messageToActivity="null";

        if(MyService.connectStatus){                                                                // if tcp connected
            new Thread(new FourthActivity.Thread2("PGR04")).start();                                // send current page name to server
            new Thread(new FourthActivity.Thread1()).start();
        }

    }


    class Thread1 implements Runnable {                             //Server message reader
        @Override
        public void run() {

            if (!running) return;                                   //Exit thread
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    receiveValue(MyService.messageToActivity);

                }
            });

        }
    }


    class Thread2 implements Runnable {                             //Phone message reader / sender
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

        if(MainActivity.startStatus){                                                               //if another activity is called stop this activity
            finish();
        }

        if(message.equals("null")) {
            new Thread(new FourthActivity.Thread1()).start();

        }else if(message.equals("run")) {
            finish();
        }else if(message.equals("teach")) {
            finish();
        }else if(message.equals("hand")) {
            finish();
        }else if(message.equals("settings")) {
            MyService.messageToActivity="null";
            new Thread(new FourthActivity.Thread1()).start();
        }else if(message.equals("monitor")) {
            finish();
        }else if(message.equals("addons")) {
            finish();
        }else if(message.equals("stop")) {
            finish();
        }else{

            MyService.messageToActivity="null";
            new Thread(new FourthActivity.Thread1()).start();
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
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);                     //animation out
    }


}
