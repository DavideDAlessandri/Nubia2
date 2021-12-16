package com.example.nubia_multipage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    SeekBar seekBarOne;
    ProgressBar progressBarOne, progressBarTwo, progressBarThree, progressBarFour, progressBarFive, progressBarSix;
    Integer progressOne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.third_ly_teach);

        seekBarOne=findViewById(R.id.seekBarOne3);
        progressBarOne=findViewById(R.id.progressBarOne3);
        progressBarTwo=findViewById(R.id.progressBarTwo3);
        progressBarThree=findViewById(R.id.progressBarThree3);
        progressBarFour=findViewById(R.id.progressBarFour3);
        progressBarFive=findViewById(R.id.progressBarFive3);
        progressBarSix=findViewById(R.id.progressBarSix3);


        seekBarOne.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getProgressOne();
                progressBarOne.setProgress(progressOne);
                progressBarTwo.setProgress(progressOne);
                progressBarThree.setProgress(progressOne);
                progressBarFour.setProgress(progressOne);
                progressBarFive.setProgress(progressOne);
                progressBarSix.setProgress(progressOne);

                if(progressOne>=66){
                    progressBarOne.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                    progressBarTwo.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                    progressBarThree.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                    progressBarFour.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                    progressBarFive.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                    progressBarSix.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_red));
                }else if(progressOne>=33){
                    progressBarOne.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                    progressBarTwo.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                    progressBarThree.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                    progressBarFour.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                    progressBarFive.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                    progressBarSix.setProgressDrawable(getDrawable(R.drawable.custom_progress_bg_yellow));
                }else if(progressOne>=0){
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

    private void getProgressOne(){
        progressOne=Integer.valueOf(seekBarOne.getProgress());
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);                     //animation out
    }
}


