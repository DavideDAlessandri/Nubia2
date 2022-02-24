package com.example.nubia_multipage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EightActivity extends AppCompatActivity {

    Boolean running=true;
    public static Activity fa8;

    SeekBar seekBarForce, seekBarSpeed, seekBarPos;
    Button openButton, closeButton, goButton;
    TextView forceTxt, speedTxt, posTxt, curPosTxt, statusTxt;
    ImageView gripperL1, gripperL2, gripperL3, gripperL4, gripperR1, gripperR2, gripperR3, gripperR4, objectIn, objectOutL, objectOutR;
    Integer progressForceVal, progressSpeedVal,progressPosVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.eight_ly_gripper);

        fa8 = this; //assign name activity 8

        seekBarForce=findViewById(R.id.seekBarForce);
        seekBarSpeed=findViewById(R.id.seekBarSpeed);
        seekBarPos=findViewById(R.id.seekBarPos);
        openButton=findViewById(R.id.openButton);
        closeButton=findViewById(R.id.closeButton);
        goButton=findViewById(R.id.goButton);
        forceTxt=findViewById(R.id.gripperForce);
        speedTxt=findViewById(R.id.gripperSpeed);
        posTxt=findViewById(R.id.gripperPos);
        curPosTxt=findViewById(R.id.gripperCursPos);
        statusTxt=findViewById(R.id.gripperStatus);

        gripperL1=findViewById(R.id.gripperL1);
        gripperL2=findViewById(R.id.gripperL2);
        gripperL3=findViewById(R.id.gripperL3);
        gripperL4=findViewById(R.id.gripperL4);
        gripperR1=findViewById(R.id.gripperR1);
        gripperR2=findViewById(R.id.gripperR2);
        gripperR3=findViewById(R.id.gripperR3);
        gripperR4=findViewById(R.id.gripperR4);
        objectIn=findViewById(R.id.objectIn);
        objectOutL=findViewById(R.id.objectOutL);
        objectOutR=findViewById(R.id.objectOutR);

        gripperL1.setVisibility(View.GONE);
        gripperL3.setVisibility(View.GONE);
        gripperL4.setVisibility(View.GONE);
        gripperR1.setVisibility(View.GONE);
        gripperR3.setVisibility(View.GONE);
        gripperR4.setVisibility(View.GONE);
        objectIn.setVisibility(View.GONE);
        objectOutL.setVisibility(View.GONE);
        objectOutR.setVisibility(View.GONE);

        seekBarForce.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getProgressForce();                                                                   //get new value
                String progressForceString =progressForceVal.toString();                                   //convert int to string
                forceTxt.setText(progressForceString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getProgressSpeed();                                                                   //get new value
                String progressSpeedString =progressSpeedVal.toString();                                   //convert int to string
                speedTxt.setText(progressSpeedString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarPos.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getProgressPos();                                                                   //get new value
                String progressPosString =progressPosVal.toString();                                   //convert int to string
                posTxt.setText(progressPosString);
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

        MyService.currentPage=8;

        MainActivity.screenSaverOn=false;
        MainActivity.startStatus=false;                                                             //reset start status (stop when change activity)
        running=true;

        if(MyService.connectStatus){                                                                //If tcp connected
            //new Thread(new SecondActivity.Thread2("PGR08")).start();                                // send current page name to server
        }
    }



    private void getProgressForce(){
        progressForceVal=Integer.valueOf(seekBarForce.getProgress());
    }

    private void getProgressSpeed(){
        progressSpeedVal=Integer.valueOf(seekBarSpeed.getProgress());
    }

    private void getProgressPos(){
        progressPosVal=Integer.valueOf(seekBarPos.getProgress());
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
