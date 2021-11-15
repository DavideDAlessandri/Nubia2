package com.example.nubia_multipage;

import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

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
        setContentView(R.layout.third_layout);

        seekBarOne=findViewById(R.id.seekBarOne3);
        progressBarOne=findViewById(R.id.progressBarOne3);
        progressBarTwo=findViewById(R.id.progressBarTwo3);
        progressBarThree=findViewById(R.id.progressBarThree3);
        progressBarFour=findViewById(R.id.progressBarFour3);
        progressBarFive=findViewById(R.id.progressBarFive3);
        progressBarSix=findViewById(R.id.progressBarSix3);

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
