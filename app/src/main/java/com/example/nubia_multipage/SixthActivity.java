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

        checkBoxA1.setChecked(true);
        seekBarLimitA1.setVisibility(View.VISIBLE);
        seekBarLimitA2.setVisibility(View.GONE);
        seekBarLimitA3.setVisibility(View.GONE);
        seekBarLimitA4.setVisibility(View.GONE);
        seekBarLimitA5.setVisibility(View.GONE);
        seekBarLimitA6.setVisibility(View.GONE);


        checkBoxA1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                if(checkBoxA1.isChecked()){
                    seekBarLimitA1.setVisibility(View.VISIBLE);
                    checkBoxA2.setChecked(false);
                    checkBoxA3.setChecked(false);
                    checkBoxA4.setChecked(false);
                    checkBoxA5.setChecked(false);
                    checkBoxA6.setChecked(false);
                    seekBarLimitA2.setVisibility(View.GONE);
                    seekBarLimitA3.setVisibility(View.GONE);
                    seekBarLimitA4.setVisibility(View.GONE);
                    seekBarLimitA5.setVisibility(View.GONE);
                    seekBarLimitA6.setVisibility(View.GONE);

                    getProgressLimitA1();
                    String progressA1String=progressA1.toString();
                    limitText.setText(progressA1String);

                }else{
                    seekBarLimitA1.setVisibility(View.GONE);

                }
            }
        });

        checkBoxA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxA2.isChecked()){
                    seekBarLimitA2.setVisibility(View.VISIBLE);
                    checkBoxA1.setChecked(false);
                    checkBoxA3.setChecked(false);
                    checkBoxA4.setChecked(false);
                    checkBoxA5.setChecked(false);
                    checkBoxA6.setChecked(false);
                    seekBarLimitA1.setVisibility(View.GONE);
                    seekBarLimitA3.setVisibility(View.GONE);
                    seekBarLimitA4.setVisibility(View.GONE);
                    seekBarLimitA5.setVisibility(View.GONE);
                    seekBarLimitA6.setVisibility(View.GONE);

                    getProgressLimitA2();
                    String progressA2String=progressA2.toString();
                    limitText.setText(progressA2String);

                }else {
                    seekBarLimitA2.setVisibility(View.GONE);

                }
            }
        });

        checkBoxA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxA3.isChecked()){
                    seekBarLimitA3.setVisibility(View.VISIBLE);
                    checkBoxA2.setChecked(false);
                    checkBoxA1.setChecked(false);
                    checkBoxA4.setChecked(false);
                    checkBoxA5.setChecked(false);
                    checkBoxA6.setChecked(false);
                    seekBarLimitA2.setVisibility(View.GONE);
                    seekBarLimitA1.setVisibility(View.GONE);
                    seekBarLimitA4.setVisibility(View.GONE);
                    seekBarLimitA5.setVisibility(View.GONE);
                    seekBarLimitA6.setVisibility(View.GONE);

                    getProgressLimitA3();
                    String progressA3String=progressA3.toString();
                    limitText.setText(progressA3String);

                }else {
                    seekBarLimitA3.setVisibility(View.GONE);

                }
            }
        });

        checkBoxA4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxA4.isChecked()){
                    seekBarLimitA4.setVisibility(View.VISIBLE);
                    checkBoxA2.setChecked(false);
                    checkBoxA3.setChecked(false);
                    checkBoxA1.setChecked(false);
                    checkBoxA5.setChecked(false);
                    checkBoxA6.setChecked(false);
                    seekBarLimitA2.setVisibility(View.GONE);
                    seekBarLimitA3.setVisibility(View.GONE);
                    seekBarLimitA1.setVisibility(View.GONE);
                    seekBarLimitA5.setVisibility(View.GONE);
                    seekBarLimitA6.setVisibility(View.GONE);

                    getProgressLimitA4();
                    String progressA4String=progressA4.toString();
                    limitText.setText(progressA4String);

                }else {
                    seekBarLimitA4.setVisibility(View.GONE);

                }
            }
        });

        checkBoxA5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxA5.isChecked()){
                    seekBarLimitA5.setVisibility(View.VISIBLE);
                    checkBoxA2.setChecked(false);
                    checkBoxA3.setChecked(false);
                    checkBoxA4.setChecked(false);
                    checkBoxA1.setChecked(false);
                    checkBoxA6.setChecked(false);
                    seekBarLimitA2.setVisibility(View.GONE);
                    seekBarLimitA3.setVisibility(View.GONE);
                    seekBarLimitA4.setVisibility(View.GONE);
                    seekBarLimitA1.setVisibility(View.GONE);
                    seekBarLimitA6.setVisibility(View.GONE);

                    getProgressLimitA5();
                    String progressA5String=progressA5.toString();
                    limitText.setText(progressA5String);

                }else {
                    seekBarLimitA5.setVisibility(View.GONE);

                }
            }
        });

        checkBoxA6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxA6.isChecked()){
                    seekBarLimitA6.setVisibility(View.VISIBLE);
                    checkBoxA2.setChecked(false);
                    checkBoxA3.setChecked(false);
                    checkBoxA4.setChecked(false);
                    checkBoxA5.setChecked(false);
                    checkBoxA1.setChecked(false);
                    seekBarLimitA2.setVisibility(View.GONE);
                    seekBarLimitA3.setVisibility(View.GONE);
                    seekBarLimitA4.setVisibility(View.GONE);
                    seekBarLimitA5.setVisibility(View.GONE);
                    seekBarLimitA1.setVisibility(View.GONE);

                    getProgressLimitA6();
                    String progressA6String=progressA6.toString();
                    limitText.setText(progressA6String);

                }else {
                    seekBarLimitA6.setVisibility(View.GONE);

                }
            }
        });

        seekBarLimitA1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getProgressLimitA1();
                String progressA1String=progressA1.toString();
                limitText.setText(progressA1String);
                progressBar1.setProgress(progressA1);
                if(progressA1>=66){
                    progressBar1.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                }else if(progressA1>=33){
                    progressBar1.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                }else if(progressA1>=0){
                    progressBar1.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarLimitA2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getProgressLimitA2();
                String progressA2String=progressA2.toString();
                limitText.setText(progressA2String);
                progressBar2.setProgress(progressA2);
                if(progressA2>=66){
                    progressBar2.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                }else if(progressA2>=33){
                    progressBar2.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                }else if(progressA2>=0){
                    progressBar2.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarLimitA3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getProgressLimitA3();
                String progressA3String=progressA3.toString();
                limitText.setText(progressA3String);
                progressBar3.setProgress(progressA3);
                if(progressA3>=66){
                    progressBar3.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                }else if(progressA3>=33){
                    progressBar3.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                }else if(progressA3>=0){
                    progressBar3.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarLimitA4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getProgressLimitA4();
                String progressA4String=progressA4.toString();
                limitText.setText(progressA4String);
                progressBar4.setProgress(progressA4);
                if(progressA4>=66){
                    progressBar4.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                }else if(progressA4>=33){
                    progressBar4.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                }else if(progressA4>=0){
                    progressBar4.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarLimitA5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getProgressLimitA5();
                String progressA5String=progressA5.toString();
                limitText.setText(progressA5String);
                progressBar5.setProgress(progressA5);
                if(progressA5>=66){
                    progressBar5.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                }else if(progressA5>=33){
                    progressBar5.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                }else if(progressA5>=0){
                    progressBar5.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarLimitA6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getProgressLimitA6();
                String progressA6String=progressA6.toString();
                limitText.setText(progressA6String);
                progressBar6.setProgress(progressA6);
                if(progressA6>=66){
                    progressBar6.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                }else if(progressA6>=33){
                    progressBar6.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                }else if(progressA6>=0){
                    progressBar6.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_green));
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

    private void getProgressLimitA1(){
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
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);                     //animation out
    }


}
