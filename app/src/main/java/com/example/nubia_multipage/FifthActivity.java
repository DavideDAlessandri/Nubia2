package com.example.nubia_multipage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class FifthActivity extends AppCompatActivity {

    ProgressBar progressBarOne, progressBarTwo, progressBarThree, progressBarFour, progressBarFive, progressBarSix;
    ToggleButton checkBoxX, checkBoxY, checkBoxZ, checkBoxA, checkBoxB, checkBoxC;
    SeekBar seekBarOVR;
    Integer progressOVR;

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

        seekBarOVR=findViewById(R.id.seekBarOVR);

        checkBoxX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBoxX.isChecked()){
                    new Thread(new FifthActivity.Thread2("X=1")).start();
                }else {
                    new Thread(new FifthActivity.Thread2("X=0")).start();
                }

            }
        });

        checkBoxY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxY.isChecked()){
                    new Thread(new FifthActivity.Thread2("Y=1")).start();
                }else {
                    new Thread(new FifthActivity.Thread2("Y=0")).start();
                }
            }
        });

        checkBoxZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxZ.isChecked()){
                    new Thread(new FifthActivity.Thread2("Z=1")).start();
                }else {
                    new Thread(new FifthActivity.Thread2("Z=0")).start();
                }
            }
        });

        checkBoxA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxA.isChecked()){
                    new Thread(new FifthActivity.Thread2("A=1")).start();
                }else {
                    new Thread(new FifthActivity.Thread2("A=0")).start();
                }
            }
        });

        checkBoxB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxB.isChecked()){
                    new Thread(new FifthActivity.Thread2("B=1")).start();
                }else {
                    new Thread(new FifthActivity.Thread2("B=0")).start();
                }
            }
        });

        checkBoxC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxC.isChecked()){
                    new Thread(new FifthActivity.Thread2("C=1")).start();
                }else {
                    new Thread(new FifthActivity.Thread2("C=0")).start();
                }
            }
        });

        seekBarOVR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getProgressOVR();

                progressBarOne.setProgress(progressOVR);
                progressBarTwo.setProgress(progressOVR);
                progressBarThree.setProgress(progressOVR);
                progressBarFour.setProgress(progressOVR);
                progressBarFive.setProgress(progressOVR);
                progressBarSix.setProgress(progressOVR);

                if(progressOVR>=66){
                    progressBarOne.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                    progressBarTwo.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                    progressBarThree.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                    progressBarFour.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                    progressBarFive.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                    progressBarSix.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                }else if(progressOVR>=33){
                    progressBarOne.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                    progressBarTwo.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                    progressBarThree.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                    progressBarFour.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                    progressBarFive.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                    progressBarSix.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                }else if(progressOVR>=0){
                    progressBarOne.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
                    progressBarTwo.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
                    progressBarThree.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
                    progressBarFour.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
                    progressBarFive.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
                    progressBarSix.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    protected  void onResume(){
        super.onResume();

        running=true;
        MyService.messageToActivity="null";
        new Thread(new FifthActivity.Thread1()).start();

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

        if(message.equals("null")){
            new Thread(new FifthActivity.Thread1()).start();
        }else {

            String val1 = message.substring(0, 3);             //get progressbar 1 value
            String val2 = message.substring(3, 6);             //get progressbar 2 value
            String val3 = message.substring(6, 9);
            String val4 = message.substring(9, 12);
            String val5 = message.substring(12, 15);
            String val6 = message.substring(15, 18);

            String val7 = message.substring(18, 19);           //get checkBox X value
            String val8 = message.substring(19, 20);           //get checkBox Y value
            String val9 = message.substring(20, 21);           //get checkBox Z value
            String val10 = message.substring(21, 22);          //get checkBox A value
            String val11 = message.substring(22, 23);          //get checkBox B value
            String val12 = message.substring(23, 24);          //get checkBox C value
            //String val13 = message.substring(24,25);          //get checkBox N value

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

            if (val7.equals("1")) {
                checkBoxX.setChecked(true);                 //set check box
            } else {
                checkBoxX.setChecked(false);
            }

            if (val8.equals("1")) {
                checkBoxY.setChecked(true);
            } else {
                checkBoxY.setChecked(false);
            }

            if (val9.equals("1")) {
                checkBoxZ.setChecked(true);
            } else {
                checkBoxZ.setChecked(false);
            }

            if (val10.equals("1")) {
                checkBoxA.setChecked(true);
            } else {
                checkBoxA.setChecked(false);
            }

            if (val11.equals("1")) {
                checkBoxB.setChecked(true);
            } else {
                checkBoxB.setChecked(false);
            }

            if (val12.equals("1")) {
                checkBoxC.setChecked(true);
            } else {
                checkBoxC.setChecked(false);
            }

            MyService.messageToActivity = "null";
            new Thread(new FifthActivity.Thread1()).start();
        }

    }

    private void changeActivityFour(){                                      //Click change page
        Intent intent = new Intent(this,FourthActivity.class);
        startActivity(intent);
        running=false;                                                      //Stop thread 1
    }

    @Override
    protected void onStop() {

        super.onStop();
        running=false;                                                      //Stop thread 1
    }

    private void getProgressOVR(){
        progressOVR=Integer.valueOf(seekBarOVR.getProgress());
    }

}
