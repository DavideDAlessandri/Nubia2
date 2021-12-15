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

    TextView percOne,percTwo;
    SeekBar seekBarOne,seekBarTwo;
    String progressOne,progressTwo;
    String switchOneView;
    Button backButton;
    Button sixthPage;
    Switch switchOne;
    ToggleButton toggleButton1;
    String toggleButton1St;
    Boolean running=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.fourth_ly_settings);

        percOne= findViewById(R.id.percOne);
        percTwo= findViewById(R.id.percTwo);
        seekBarOne= findViewById(R.id.seekBarOne5);
        seekBarTwo=findViewById(R.id.seekBarTwo);
        backButton= findViewById(R.id.backButton);
        sixthPage=findViewById(R.id.activitySixButton);
        switchOne= findViewById(R.id.switchOne);
        toggleButton1=findViewById(R.id.toggleButton1);

        backButton.setPaintFlags(backButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        sixthPage.setPaintFlags(sixthPage.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        seekBarOne.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getProgressOne();
                percOne.setText(progressOne+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarTwo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getProgressTwo();
                percTwo.setText(progressTwo+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        switchOneView="off";
        switchOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchOneView="on";
                } else {
                    switchOneView="off";
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sixthPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivitySix();
            }
        });

        toggleButton1St="off";
        toggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {     //toggle button
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton1St="on";
                } else {
                    toggleButton1St="off";
                }
            }
        });

    }

    @Override
    protected  void onResume(){
        super.onResume();

        if (MyService.connectStatus.equals(true)){
            running=true;
            MyService.messageToActivity="null";
            new Thread(new FourthActivity.Thread1()).start();

            new Timer().scheduleAtFixedRate(new TimerTask() {                                           //send data every second
                @Override
                public void run() {
                    if (!running) return;
                    getProgressOne();
                    getProgressTwo();
                    new Thread(new Thread2("-"+progressOne+"-"+progressTwo+"-"+switchOneView+"-"+toggleButton1St+"-")).start();
                }
            }, 0, 1000);//1000 milliseconds=1 second

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

    private void getProgressOne(){
        progressOne=String.valueOf(seekBarOne.getProgress());
    }

    private  void getProgressTwo(){
        progressTwo=String.valueOf(seekBarTwo.getProgress());
    }

    private  void receiveValue(String message){

        if(message.equals("null")){
            new Thread(new FourthActivity.Thread1()).start();
        }else{
            String val1=message.substring(0,2);             //get seekbar 1 value
            String val2=message.substring(2,4);             //get seekbar 2 value
            String sw1=message.substring(4,5);

            int number1 = Integer.parseInt(val1);           //set seekbar 1 value
            seekBarOne.setProgress(number1);
            if(val1.equals("99")){
                seekBarOne.setProgress(100);
            }

            int number2 = Integer.parseInt(val2);           //set seekbar 2 value
            seekBarTwo.setProgress(number2);
            if(val2.equals("99")){
                seekBarTwo.setProgress(100);
            }

            if(sw1.equals("1")){                            //set switch 1 value
                toggleButton1.setChecked(true);
            }else{
                toggleButton1.setChecked(false);
            }


            MyService.messageToActivity="null";
            new Thread(new FourthActivity.Thread1()).start();
        }

    }

    private void changeActivitySix(){                                                               //Change activity
        Intent intent = new Intent(this,SixthActivity.class);
        startActivity(intent);
        running=false;
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
