package com.example.nubia_multipage;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class FifthActivity extends AppCompatActivity {

    SeekBar seekBarOne;
    ProgressBar progressBarOne, progressBarTwo, progressBarThree, progressBarFour, progressBarFive, progressBarSix;
    Integer progressOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.fifth_layout);

        seekBarOne=findViewById(R.id.seekBarOne5);
        progressBarOne=findViewById(R.id.progressBarOne5);
        progressBarTwo=findViewById(R.id.progressBarTwo5);
        progressBarThree=findViewById(R.id.progressBarThree5);
        progressBarFour=findViewById(R.id.progressBarFour5);
        progressBarFive=findViewById(R.id.progressBarFive5);
        progressBarSix=findViewById(R.id.progressBarSix5);


        seekBarOne.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getProgressOne();
                progressBarOne.setProgress(progressOne);
                progressBarTwo.setProgress(progressOne);
                progressBarThree.setProgress(progressOne);
                progressBarFour.setProgress(progressOne);
                progressBarFive.setProgress(progressOne);
                progressBarSix.setProgress(progressOne);
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


}
