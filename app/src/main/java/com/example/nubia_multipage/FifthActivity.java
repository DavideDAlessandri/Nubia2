package com.example.nubia_multipage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class FifthActivity extends AppCompatActivity {

    ProgressBar progressBarOne, progressBarTwo, progressBarThree, progressBarFour, progressBarFive, progressBarSix;
    ToggleButton checkBoxX, checkBoxY, checkBoxZ, checkBoxA, checkBoxB, checkBoxC, checkBoxN;
    SeekBar seekBarOVR;
    Integer progressOVR;
    ImageView iLAxisX, iLAxisY, iLAxisZ, iRAxisX, iRAxisY, iRAxisZ;
    TextView ovrText;
    String X,Y,Z,A,B,C;

    Boolean running=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.fifth_ly_hand);

        progressBarOne=findViewById(R.id.progressBarOne5);
        progressBarTwo=findViewById(R.id.progressBarTwo5);
        progressBarThree=findViewById(R.id.progressBarThree5);
        progressBarFour=findViewById(R.id.progressBarFour5);
        progressBarFive=findViewById(R.id.progressBarFive5);
        progressBarSix=findViewById(R.id.progressBarSix5);

        checkBoxX=findViewById(R.id.checkBoxX);
        checkBoxY=findViewById(R.id.checkBoxY);
        checkBoxZ=findViewById(R.id.checkBoxZ);
        checkBoxA=findViewById(R.id.checkBoxA);
        checkBoxB=findViewById(R.id.checkBoxB);
        checkBoxC=findViewById(R.id.checkBoxC);
        checkBoxN=findViewById(R.id.checkBoxN);

        iLAxisX=findViewById(R.id.lAxisX);
        iLAxisY=findViewById(R.id.lAxisY);
        iLAxisZ=findViewById(R.id.lAxisZ);
        iRAxisX=findViewById(R.id.rAxisX);
        iRAxisY=findViewById(R.id.rAxisY);
        iRAxisZ=findViewById(R.id.rAxisZ);

        seekBarOVR=findViewById(R.id.seekBarOVR);
        ovrText=findViewById(R.id.OVRText);

        int lAxisX = getResources().getIdentifier("@drawable/l_axis_x",null,this.getPackageName());
        int lAxisY = getResources().getIdentifier("@drawable/l_axis_y",null,this.getPackageName());
        int lAxisZ = getResources().getIdentifier("@drawable/l_axis_z",null,this.getPackageName());
        int rAxisX = getResources().getIdentifier("@drawable/r_axis_x",null,this.getPackageName());
        int rAxisY = getResources().getIdentifier("@drawable/r_axis_y",null,this.getPackageName());
        int rAxisZ = getResources().getIdentifier("@drawable/r_axis_z",null,this.getPackageName());

        X="0";
        Y="0";
        Z="0";
        A="0";
        B="0";
        C="0";

        checkBoxX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBoxX.isChecked()){                                                          //if checkBox X checked
                    iLAxisX.setImageResource(lAxisX);                                               //set image axis
                    X="1";
                    sendAxisStatus(X,Y,Z,A,B,C);

                }else {
                    iLAxisX.setImageResource(0);                                                    //remove image
                    X="0";
                    sendAxisStatus(X,Y,Z,A,B,C);


                }

            }
        });

        checkBoxY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxY.isChecked()){
                    iLAxisY.setImageResource(lAxisY);
                    Y="1";
                    sendAxisStatus(X,Y,Z,A,B,C);
                }else {
                    iLAxisY.setImageResource(0);
                    Y="0";
                    sendAxisStatus(X,Y,Z,A,B,C);
                }
            }
        });

        checkBoxZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxZ.isChecked()){
                    iLAxisZ.setImageResource(lAxisZ);
                    Z="1";
                    sendAxisStatus(X,Y,Z,A,B,C);
                }else {
                    iLAxisZ.setImageResource(0);
                    Z="0";
                    sendAxisStatus(X,Y,Z,A,B,C);
                }
            }
        });

        checkBoxA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxA.isChecked()){
                    iRAxisX.setImageResource(rAxisX);
                    A="1";
                    sendAxisStatus(X,Y,Z,A,B,C);
                }else {
                    iRAxisX.setImageResource(0);
                    A="0";
                    sendAxisStatus(X,Y,Z,A,B,C);
                }
            }
        });

        checkBoxB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxB.isChecked()){
                    iRAxisY.setImageResource(rAxisY);
                    B="1";
                    sendAxisStatus(X,Y,Z,A,B,C);
                }else {
                    iRAxisY.setImageResource(0);
                    B="0";
                    sendAxisStatus(X,Y,Z,A,B,C);
                }
            }
        });

        checkBoxC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxC.isChecked()){
                    iRAxisZ.setImageResource(rAxisZ);
                    C="1";
                    sendAxisStatus(X,Y,Z,A,B,C);
                }else {
                    iRAxisZ.setImageResource(0);
                    C="0";
                    sendAxisStatus(X,Y,Z,A,B,C);
                }
            }
        });

        seekBarOVR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {               //seekBar OVR
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                getProgressOVR();                                                                   //get new value
                String progressOVRString =progressOVR.toString();                                   //convert int to string
                ovrText.setText(progressOVRString);                                                 //display value

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {                                      //when value change

                getProgressOVR();                                                                   //get new value
                String progressOVRString =progressOVR.toString();                                   //convert int to string
                if(progressOVR>10 && progressOVR<100){                                                                 //if<100 send 0ss
                    new Thread(new FifthActivity.Thread2("OVR0"+progressOVRString)).start();        // send value to server
                }else if(progressOVR<10){
                    new Thread(new FifthActivity.Thread2("OVR00"+progressOVRString)).start();         // send value to server
                }else{
                    new Thread(new FifthActivity.Thread2("OVR"+progressOVRString)).start();
                }


            }
        });

    }

    @Override
    protected  void onResume(){
        super.onResume();

        MainActivity.startStatus=false;                                                             //reset start status (stop when change activity)
        running=true;
        MyService.messageToActivity="null";

        if(MyService.connectStatus){                                                                // if tcp connected
            new Thread(new FifthActivity.Thread2("PGR05")).start();                          // send current page name to server
            new Thread(new FifthActivity.Thread1()).start();
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

    @SuppressLint("UseCompatLoadingForDrawables")
    private  void receiveValue(String message){

        int lAxisX = getResources().getIdentifier("@drawable/l_axis_x",null,this.getPackageName());
        int lAxisY = getResources().getIdentifier("@drawable/l_axis_y",null,this.getPackageName());
        int lAxisZ = getResources().getIdentifier("@drawable/l_axis_z",null,this.getPackageName());
        int rAxisX = getResources().getIdentifier("@drawable/r_axis_x",null,this.getPackageName());
        int rAxisY = getResources().getIdentifier("@drawable/r_axis_y",null,this.getPackageName());
        int rAxisZ = getResources().getIdentifier("@drawable/r_axis_z",null,this.getPackageName());

        if(MainActivity.startStatus){                                                               //if another activity is called stop this activity
            finish();
        }

        String referenceMessage=message.substring(0,3);                                             //Identify message reference

        if(message.equals("null")) {
            new Thread(new FifthActivity.Thread1()).start();
        }else if(message.equals("PGD02")) {
            finish();
        }else if(message.equals("PGD03")) {
            finish();
        }else if(message.equals("PGD05")) {
            MyService.messageToActivity = "null";
            new Thread(new FifthActivity.Thread1()).start();
        }else if(message.equals("PGD04")) {
            finish();
        }else if(message.equals("PGD06")) {
            finish();
        }else if(message.equals("PGD07")) {
            finish();
        }else if(message.equals("PGD01")) {
            finish();
        }else if(referenceMessage.equals("FTD")){

            String val1 = message.substring(3, 6);             //get progressbar 1 value
            String val2 = message.substring(6, 9);             //get progressbar 2 value
            String val3 = message.substring(9, 12);
            String val4 = message.substring(12, 14);
            String val5 = message.substring(14, 16);
            String val6 = message.substring(16, 18);


            int number1 = Integer.parseInt(val1);           //set progressbar 1 value
            progressBarOne.setProgress(number1);
            if(number1>=200){
                progressBarOne.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
            }else if(number1>=100){
                progressBarOne.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
            }else if(number1>=0){
                progressBarOne.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
            }

            int number2 = Integer.parseInt(val2);           //set progressbar 2 value
            progressBarTwo.setProgress(number2);
            if(number2>=200){
                progressBarTwo.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
            }else if(number2>=100){
                progressBarTwo.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
            }else if(number2>=0){
                progressBarTwo.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
            }

            int number3 = Integer.parseInt(val3);
            progressBarThree.setProgress(number3);
            if(number3>=200){
                progressBarThree.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
            }else if(number3>=100){
                progressBarThree.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
            }else if(number3>=0){
                progressBarThree.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
            }

            int number4 = Integer.parseInt(val4);
            progressBarFour.setProgress(number4);
            if(number4>=20){
                progressBarFour.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
            }else if(number4>=10){
                progressBarFour.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
            }else if(number4>=0){
                progressBarFour.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
            }

            int number5 = Integer.parseInt(val5);
            progressBarFive.setProgress(number5);
            if(number5>=20){
                progressBarFive.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
            }else if(number5>=10){
                progressBarFive.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
            }else if(number5>=0){
                progressBarFive.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
            }

            int number6 = Integer.parseInt(val6);
            progressBarSix.setProgress(number6);
            if(number6>=20){
                progressBarSix.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
            }else if(number6>=10){
                progressBarSix.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
            }else if(number6>=0){
                progressBarSix.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
            }

            MyService.messageToActivity = "null";
            new Thread(new FifthActivity.Thread1()).start();

        }else if(referenceMessage.equals("OVD")){

            String val14 = message.substring(3,6);            //get seekbar progress

            int number14 = Integer.parseInt(val14);
            seekBarOVR.setProgress(number14);

            MyService.messageToActivity = "null";
            new Thread(new FifthActivity.Thread1()).start();

        }else if(referenceMessage.equals("HGD")){

            String val7 = message.substring(3, 4);           //get checkBox X value
            String val8 = message.substring(4, 5);           //get checkBox Y value
            String val9 = message.substring(5, 6);           //get checkBox Z value
            String val10 = message.substring(6, 7);          //get checkBox A value
            String val11 = message.substring(7, 8);          //get checkBox B value
            String val12 = message.substring(8, 9);          //get checkBox C value

            if (val7.equals("1")) {
                checkBoxX.setChecked(true);                                                         //set check box
                iLAxisX.setImageResource(lAxisX);                                                   //set axis image
                X="1";                                                                              //update value to send
            } else {
                checkBoxX.setChecked(false);
                iLAxisX.setImageResource(0);                                                        //remove axis image
                X="0";
            }

            if (val8.equals("1")) {
                checkBoxY.setChecked(true);
                iLAxisY.setImageResource(lAxisY);
                Y="1";
            } else {
                checkBoxY.setChecked(false);
                iLAxisY.setImageResource(0);
                Y="0";
            }

            if (val9.equals("1")) {
                checkBoxZ.setChecked(true);
                iLAxisZ.setImageResource(lAxisZ);
                Z="1";
            } else {
                checkBoxZ.setChecked(false);
                iLAxisZ.setImageResource(0);
                Z="0";
            }

            if (val10.equals("1")) {
                checkBoxA.setChecked(true);
                iRAxisX.setImageResource(rAxisX);
                A="1";
            } else {
                checkBoxA.setChecked(false);
                iRAxisX.setImageResource(0);
                A="0";
            }

            if (val11.equals("1")) {
                checkBoxB.setChecked(true);
                iRAxisY.setImageResource(rAxisY);
                B="1";
            } else {
                checkBoxB.setChecked(false);
                iRAxisY.setImageResource(0);
                B="0";
            }

            if (val12.equals("1")) {
                checkBoxC.setChecked(true);
                iRAxisZ.setImageResource(rAxisZ);
                C="1";
            } else {
                checkBoxC.setChecked(false);
                iRAxisZ.setImageResource(0);
                C="0";
            }

            MyService.messageToActivity = "null";
            new Thread(new FifthActivity.Thread1()).start();


        }else{

            MyService.messageToActivity = "null";
            new Thread(new FifthActivity.Thread1()).start();

        }

    }

    private void sendAxisStatus(String X, String Y, String Z, String A, String B, String C){  //Send to robot hand guiding button status

        new Thread(new FifthActivity.Thread2("HGR"+X+Y+Z+A+B+C)).start();                                       //HGRnnnnnn

    }

    @Override
    protected void onStop() {

        super.onStop();
        running=false;                                                                              //Stop thread 1
    }

    private void getProgressOVR(){
        progressOVR=Integer.valueOf(seekBarOVR.getProgress());
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);                     //animation out
    }

}
