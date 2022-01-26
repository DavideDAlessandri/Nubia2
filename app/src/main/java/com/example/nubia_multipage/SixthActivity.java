package com.example.nubia_multipage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class SixthActivity extends AppCompatActivity {

    ToggleButton checkBoxA1,checkBoxA2, checkBoxA3, checkBoxA4, checkBoxA5, checkBoxA6;
    SeekBar seekBarLimitA1, seekBarLimitA2, seekBarLimitA3, seekBarLimitA4, seekBarLimitA5, seekBarLimitA6;
    ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6;
    TextView limitText;
    Integer progressA1, progressA2, progressA3, progressA4, progressA5, progressA6;
    String A1,A2,A3,A4,A5,A6;

    Boolean running=true;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.sixth_ly_monitor);

        checkBoxA1=findViewById(R.id.checkBoxA1);
        checkBoxA2=findViewById(R.id.checkBoxA2);
        checkBoxA3=findViewById(R.id.checkBoxA3);
        checkBoxA4=findViewById(R.id.checkBoxA4);
        checkBoxA5=findViewById(R.id.checkBoxA5);
        checkBoxA6=findViewById(R.id.checkBoxA6);
        seekBarLimitA1=findViewById(R.id.seekBarLimitA1);
        seekBarLimitA2=findViewById(R.id.seekBarLimitA2);
        seekBarLimitA3=findViewById(R.id.seekBarLimitA3);
        seekBarLimitA4=findViewById(R.id.seekBarLimitA4);
        seekBarLimitA5=findViewById(R.id.seekBarLimitA5);
        seekBarLimitA6=findViewById(R.id.seekBarLimitA6);
        progressBar1=findViewById(R.id.progressBarOne6);
        progressBar2=findViewById(R.id.progressBarTwo6);
        progressBar3=findViewById(R.id.progressBarThree6);
        progressBar4=findViewById(R.id.progressBarFour6);
        progressBar5=findViewById(R.id.progressBarFive6);
        progressBar6=findViewById(R.id.progressBarSix6);
        limitText=findViewById(R.id.LimitText);

        checkBoxA1.setChecked(true);                                                                //On start set checkBox A1 true
        seekBarLimitA1.setVisibility(View.VISIBLE);                                                 //and view seekbar 1
        seekBarLimitA2.setVisibility(View.GONE);                                                    //hide others seekbar
        seekBarLimitA3.setVisibility(View.GONE);
        seekBarLimitA4.setVisibility(View.GONE);
        seekBarLimitA5.setVisibility(View.GONE);
        seekBarLimitA6.setVisibility(View.GONE);

        A1="0";
        A2="0";
        A3="0";
        A4="0";
        A5="0";
        A6="0";

        checkBoxA1.setOnClickListener(new View.OnClickListener() {                                  //when checkbox1 checked show seekbar 1 and hide others
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                if(checkBoxA1.isChecked()){
                    seekBarLimitA1.setVisibility(View.VISIBLE);
                    seekBarLimitA2.setVisibility(View.GONE);
                    seekBarLimitA3.setVisibility(View.GONE);
                    seekBarLimitA4.setVisibility(View.GONE);
                    seekBarLimitA5.setVisibility(View.GONE);
                    seekBarLimitA6.setVisibility(View.GONE);

                    getProgressLimitA1();                                                           //show value of seekbar 1 in textview
                    String progressA1String=progressA1.toString();
                    limitText.setText(progressA1String);

                    A1="1";
                    sendJointStatus(A1,A2,A3,A4,A5,A6);                                             // send button checked status to server

                }else{
                    A1="0";
                    sendJointStatus(A1,A2,A3,A4,A5,A6);                                             // send button checked status to server
                    seekBarLimitA1.setVisibility(View.GONE);

                }
            }
        });

        checkBoxA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxA2.isChecked()){
                    seekBarLimitA2.setVisibility(View.VISIBLE);
                    seekBarLimitA1.setVisibility(View.GONE);
                    seekBarLimitA3.setVisibility(View.GONE);
                    seekBarLimitA4.setVisibility(View.GONE);
                    seekBarLimitA5.setVisibility(View.GONE);
                    seekBarLimitA6.setVisibility(View.GONE);

                    getProgressLimitA2();
                    String progressA2String=progressA2.toString();
                    limitText.setText(progressA2String);

                    A2="1";
                    sendJointStatus(A1,A2,A3,A4,A5,A6);                                             // send button checked status to server

                }else {
                    seekBarLimitA2.setVisibility(View.GONE);
                    A2="0";
                    sendJointStatus(A1,A2,A3,A4,A5,A6);                                             // send button checked status to server

                }
            }
        });

        checkBoxA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxA3.isChecked()){
                    seekBarLimitA3.setVisibility(View.VISIBLE);
                    seekBarLimitA2.setVisibility(View.GONE);
                    seekBarLimitA1.setVisibility(View.GONE);
                    seekBarLimitA4.setVisibility(View.GONE);
                    seekBarLimitA5.setVisibility(View.GONE);
                    seekBarLimitA6.setVisibility(View.GONE);

                    getProgressLimitA3();
                    String progressA3String=progressA3.toString();
                    limitText.setText(progressA3String);

                    A3="1";
                    sendJointStatus(A1,A2,A3,A4,A5,A6);                                             // send button checked status to server

                }else {
                    seekBarLimitA3.setVisibility(View.GONE);
                    A3="0";
                    sendJointStatus(A1,A2,A3,A4,A5,A6);                                             // send button checked status to server

                }
            }
        });

        checkBoxA4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxA4.isChecked()){
                    seekBarLimitA4.setVisibility(View.VISIBLE);
                    seekBarLimitA2.setVisibility(View.GONE);
                    seekBarLimitA3.setVisibility(View.GONE);
                    seekBarLimitA1.setVisibility(View.GONE);
                    seekBarLimitA5.setVisibility(View.GONE);
                    seekBarLimitA6.setVisibility(View.GONE);

                    getProgressLimitA4();
                    String progressA4String=progressA4.toString();
                    limitText.setText(progressA4String);

                    A4="1";
                    sendJointStatus(A1,A2,A3,A4,A5,A6);                                             // send button checked status to server

                }else {
                    seekBarLimitA4.setVisibility(View.GONE);
                    A4="0";
                    sendJointStatus(A1,A2,A3,A4,A5,A6);                                             // send button checked status to server

                }
            }
        });

        checkBoxA5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxA5.isChecked()){
                    seekBarLimitA5.setVisibility(View.VISIBLE);
                    seekBarLimitA2.setVisibility(View.GONE);
                    seekBarLimitA3.setVisibility(View.GONE);
                    seekBarLimitA4.setVisibility(View.GONE);
                    seekBarLimitA1.setVisibility(View.GONE);
                    seekBarLimitA6.setVisibility(View.GONE);

                    getProgressLimitA5();
                    String progressA5String=progressA5.toString();
                    limitText.setText(progressA5String);

                    A5="1";
                    sendJointStatus(A1,A2,A3,A4,A5,A6);                                             // send button checked status to server

                }else {
                    seekBarLimitA5.setVisibility(View.GONE);
                    A5="0";
                    sendJointStatus(A1,A2,A3,A4,A5,A6);                                             // send button checked status to server

                }
            }
        });

        checkBoxA6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxA6.isChecked()){
                    seekBarLimitA6.setVisibility(View.VISIBLE);
                    seekBarLimitA2.setVisibility(View.GONE);
                    seekBarLimitA3.setVisibility(View.GONE);
                    seekBarLimitA4.setVisibility(View.GONE);
                    seekBarLimitA5.setVisibility(View.GONE);
                    seekBarLimitA1.setVisibility(View.GONE);

                    getProgressLimitA6();
                    String progressA6String=progressA6.toString();
                    limitText.setText(progressA6String);

                    A6="1";
                    sendJointStatus(A1,A2,A3,A4,A5,A6);                                             // send button checked status to server

                }else {
                    seekBarLimitA6.setVisibility(View.GONE);
                    A6="0";
                    sendJointStatus(A1,A2,A3,A4,A5,A6);                                             // send button checked status to server

                }
            }
        });

        seekBarLimitA1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {           //on seekbar progress update text and change graphic value and color
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                getProgressLimitA1();
                String progressA1String=progressA1.toString();
                limitText.setText(progressA1String);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                getProgressLimitA1();
                String progressA1String=progressA1.toString();                                      //get new value
                if(progressA1<100){                                                                 //if<100 send 0ss
                    new Thread(new SixthActivity.Thread2("TLR10"+progressA1String)).start();        // send value to server
                }else{
                    new Thread(new SixthActivity.Thread2("TLR1"+progressA1String)).start();         // send value to server
                }
            }
        });

        seekBarLimitA2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                getProgressLimitA2();
                String progressA2String=progressA2.toString();
                limitText.setText(progressA2String);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                getProgressLimitA2();
                String progressA2String=progressA2.toString();
                limitText.setText(progressA2String);
                if(progressA2<100){                                                                 //if<100 send 0ss
                    new Thread(new SixthActivity.Thread2("TLR20"+progressA2String)).start();        // send value to server
                }else{
                    new Thread(new SixthActivity.Thread2("TLR2"+progressA2String)).start();         // send value to server
                }
            }
        });

        seekBarLimitA3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                getProgressLimitA3();
                String progressA3String=progressA3.toString();
                limitText.setText(progressA3String);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                getProgressLimitA3();
                String progressA3String=progressA3.toString();
                limitText.setText(progressA3String);
                if(progressA3<100){                                                                 //if<100 send 0ss
                    new Thread(new SixthActivity.Thread2("TLR30"+progressA3String)).start();        // send value to server
                }else{
                    new Thread(new SixthActivity.Thread2("TLR3"+progressA3String)).start();         // send value to server
                }
            }
        });

        seekBarLimitA4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                getProgressLimitA4();
                String progressA4String=progressA4.toString();
                limitText.setText(progressA4String);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                getProgressLimitA4();
                String progressA4String=progressA4.toString();
                limitText.setText(progressA4String);

                if(progressA4<100){                                                                 //if<100 send 0ss
                    new Thread(new SixthActivity.Thread2("TLR40"+progressA4String)).start();        // send value to server
                }else{
                    new Thread(new SixthActivity.Thread2("TLR4"+progressA4String)).start();         // send value to server
                }

            }
        });

        seekBarLimitA5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                getProgressLimitA5();
                String progressA5String=progressA5.toString();
                limitText.setText(progressA5String);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                getProgressLimitA5();
                String progressA5String=progressA5.toString();
                limitText.setText(progressA5String);
                if(progressA5<100){                                                                 //if<100 send 0ss
                    new Thread(new SixthActivity.Thread2("TLR50"+progressA5String)).start();        // send value to server
                }else{
                    new Thread(new SixthActivity.Thread2("TLR5"+progressA5String)).start();         // send value to server
                }
            }
        });

        seekBarLimitA6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                getProgressLimitA5();
                String progressA5String=progressA5.toString();
                limitText.setText(progressA5String);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                getProgressLimitA6();
                String progressA6String=progressA6.toString();
                limitText.setText(progressA6String);
                if(progressA6<100){                                                                 //if<100 send 0ss
                    new Thread(new SixthActivity.Thread2("TLR60"+progressA6String)).start();        // send value to server
                }else{
                    new Thread(new SixthActivity.Thread2("TLR6"+progressA6String)).start();         // send value to server
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
            new Thread(new SixthActivity.Thread2("PGR06")).start();                       // send current page name to server
            new Thread(new SixthActivity.Thread1()).start();
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

    @SuppressLint("UseCompatLoadingForDrawables")
    private  void receiveValue(String message){

        String referenceMessage=message.substring(0,3);                                             //Identify message reference

        if(message.equals("null")) {
            new Thread(new SixthActivity.Thread1()).start();
        }else if(message.equals("PGD02")) {
            finish();
        }else if(message.equals("PGD03")) {
            finish();
        }else if(message.equals("PGD04")) {
            finish();
        }else if(message.equals("PGD05")) {
            finish();
        }else if(message.equals("PGD06")) {
            MyService.messageToActivity = "null";
            new Thread(new SixthActivity.Thread1()).start();
        }else if(message.equals("PGD07")) {
            finish();
        }else if(message.equals("PGD01")) {
            finish();
        }else if(referenceMessage.equals("TID")) {                                                       //if initial value of message = A => change button display settings

            String ch1 = message.substring(3, 4);                                                   //get progressbar checkbox 1 value
            String ch2 = message.substring(4, 5);
            String ch3 = message.substring(5, 6);
            String ch4 = message.substring(6, 7);
            String ch5 = message.substring(7, 8);
            String ch6 = message.substring(8, 9);

            if(ch1.equals("1")){
                checkBoxA1.setChecked(true);
                seekBarLimitA1.setVisibility(View.GONE);
            }else{
                checkBoxA1.setChecked(false);
                seekBarLimitA1.setVisibility(View.GONE);
            }

            if(ch2.equals("1")){
                checkBoxA2.setChecked(true);
                seekBarLimitA2.setVisibility(View.GONE);
            }else{
                checkBoxA2.setChecked(false);
                seekBarLimitA2.setVisibility(View.GONE);
            }

            if(ch3.equals("1")){
                checkBoxA3.setChecked(true);
                seekBarLimitA3.setVisibility(View.GONE);
            }else{
                checkBoxA3.setChecked(false);
                seekBarLimitA3.setVisibility(View.GONE);
            }

            if(ch4.equals("1")){
                checkBoxA4.setChecked(true);
                seekBarLimitA4.setVisibility(View.GONE);
            }else{
                checkBoxA4.setChecked(false);
                seekBarLimitA4.setVisibility(View.GONE);
            }

            if(ch5.equals("1")){
                checkBoxA5.setChecked(true);
                seekBarLimitA5.setVisibility(View.GONE);
            }else{
                checkBoxA5.setChecked(false);
                seekBarLimitA5.setVisibility(View.GONE);
            }

            if(ch6.equals("1")){
                checkBoxA6.setChecked(true);
                seekBarLimitA6.setVisibility(View.GONE);
            }else{
                checkBoxA6.setChecked(false);
                seekBarLimitA6.setVisibility(View.GONE);
            }

            MyService.messageToActivity = "null";
            new Thread(new SixthActivity.Thread1()).start();

        }else if(referenceMessage.equals("TOD")){

            String val1 = message.substring(3, 6);                                                  //get progressbar 1 value
            String val2 = message.substring(6, 9);                                                  //get progressbar 1 value
            String val3 = message.substring(9, 12);
            String val4 = message.substring(12, 15);
            String val5 = message.substring(15, 18);
            String val6 = message.substring(18, 21);

            int number1 = Integer.parseInt(val1);                                                   //set progressbar 1 value
            progressBar1.setProgress(number1);
            if(number1>=66){
                progressBar1.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
            }else if(number1>=33){
                progressBar1.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
            }else if(number1>=0){
                progressBar1.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
            }

            int number2 = Integer.parseInt(val2);
            progressBar2.setProgress(number2);
            if(number2>=66){
                progressBar2.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
            }else if(number2>=33){
                progressBar2.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
            }else if(number2>=0){
                progressBar2.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
            }

            int number3 = Integer.parseInt(val3);
            progressBar3.setProgress(number3);
            if(number3>=66){
                progressBar3.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
            }else if(number3>=33){
                progressBar3.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
            }else if(number3>=0){
                progressBar3.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
            }

            int number4 = Integer.parseInt(val4);
            progressBar4.setProgress(number4);
            if(number4>=66){
                progressBar4.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
            }else if(number4>=33){
                progressBar4.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
            }else if(number4>=0){
                progressBar4.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
            }

            int number5 = Integer.parseInt(val5);
            progressBar5.setProgress(number5);
            if(number5>=66){
                progressBar5.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
            }else if(number5>=33){
                progressBar5.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
            }else if(number5>=0){
                progressBar5.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
            }

            int number6 = Integer.parseInt(val6);
            progressBar6.setProgress(number6);
            if(number6>=66){
                progressBar6.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
            }else if(number6>=33){
                progressBar6.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
            }else if(number6>=0){
                progressBar6.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
            }

            MyService.messageToActivity = "null";
            new Thread(new SixthActivity.Thread1()).start();

        }else if(referenceMessage.equals("TLD")){

            //to-do

            String val7 = message.substring(4,7);                                                   //get ovr value
            int number7 = Integer.parseInt(val7);
            String number7S = String.valueOf(number7);                                              //remove the 0 (ex val7=050 => number7S=50)
            limitText.setText(number7S);

            MyService.messageToActivity = "null";
            new Thread(new SixthActivity.Thread1()).start();

        }else{
            MyService.messageToActivity = "null";
            new Thread(new SixthActivity.Thread1()).start();
        }

    }

    private void sendJointStatus(String A1, String A2, String A3, String A4, String A5, String A6){  //Send to robot hand guiding button status

        new Thread(new SixthActivity.Thread2("HGR"+A1+A2+A3+A4+A5+A6)).start();                     //TIRnnnnnn

    }

    private void getProgressLimitA1(){                                                              //get seekbar updated value
        progressA1=Integer.valueOf(seekBarLimitA1.getProgress());
    }

    private void getProgressLimitA2(){
        progressA2=Integer.valueOf(seekBarLimitA2.getProgress());
    }

    private void getProgressLimitA3(){
        progressA3=Integer.valueOf(seekBarLimitA3.getProgress());
    }

    private void getProgressLimitA4(){
        progressA4=Integer.valueOf(seekBarLimitA4.getProgress());
    }

    private void getProgressLimitA5(){
        progressA5=Integer.valueOf(seekBarLimitA5.getProgress());
    }

    private void getProgressLimitA6(){
        progressA6=Integer.valueOf(seekBarLimitA6.getProgress());
    }

    @Override
    protected void onStop() {

        super.onStop();
        running=false;                                                                              //Stop thread 1
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);                     //animation out
    }


}
