package com.example.nubia_multipage;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {

    TextView percOne,percTwo;
    SeekBar seekBarOne,seekBarTwo;
    String progressOne,progressTwo;
    Button backButton;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.fourth_layout);

        percOne=(TextView) findViewById(R.id.percOne);
        percTwo=(TextView) findViewById(R.id.percTwo);
        seekBarOne=(SeekBar) findViewById(R.id.seekBarOne);
        seekBarTwo=(SeekBar) findViewById(R.id.seekBarTwo);
        backButton=(Button) findViewById(R.id.backButton);
        sendButton=(Button) findViewById(R.id.sendData);

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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void getProgressOne(){
        progressOne=String.valueOf(seekBarOne.getProgress());
        return;
    }

    private  void getProgressTwo(){
        progressTwo=String.valueOf(seekBarTwo.getProgress());
        return;
    }

}
