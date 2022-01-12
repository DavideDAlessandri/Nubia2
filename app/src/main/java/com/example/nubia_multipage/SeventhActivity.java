package com.example.nubia_multipage;

import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class SeventhActivity extends AppCompatActivity {

    Boolean running=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.seventh_ly_add_ons);


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
        new Thread(new SeventhActivity.Thread2("AddOnsActivity")).start();                           // send current page name to server
        new Thread(new SeventhActivity.Thread1()).start();

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

            //to-do => display 1 / 2

            MyService.messageToActivity = "null";
            new Thread(new SeventhActivity.Thread1()).start();
        }else if(message.equals("stop")) {
            finish();
        }else{
            //to-do


            MyService.messageToActivity = "null";
            new Thread(new SeventhActivity.Thread1()).start();
        }
    }



}
