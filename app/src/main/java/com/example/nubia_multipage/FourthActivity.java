package com.example.nubia_multipage;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;

public class FourthActivity extends AppCompatActivity {

    TextView percOne,percTwo;
    SeekBar seekBarOne,seekBarTwo;
    String progressOne,progressTwo;
    String switchOneView;
    Button backButton;
    Button sendButton;
    Switch switchOne;

    Thread Thread1 = null;
    String SERVER_IP = "192.168.1.2";
    int SERVER_PORT = 8080;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.fourth_layout);

        percOne= findViewById(R.id.percOne);
        percTwo= findViewById(R.id.percTwo);
        seekBarOne= findViewById(R.id.seekBarOne);
        seekBarTwo=findViewById(R.id.seekBarTwo);
        backButton= findViewById(R.id.backButton);
        sendButton=findViewById(R.id.sendData);
        switchOne= findViewById(R.id.switchOne);


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

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProgressOne();
                getProgressTwo();
                new Thread(new Thread3("-"+progressOne+"-"+progressTwo+"-"+switchOneView+"-")).start();

            }
        });

        Thread1 = new Thread(new FourthActivity.Thread1());
        Thread1.start();

    }

    private PrintWriter output;
    private BufferedReader input;
    class Thread1 implements Runnable {                                //Server connection
        public void run() {
            Socket socket;
            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);           //connection server ip / port
                output = new PrintWriter(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                new Thread(new FourthActivity.Thread2()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class Thread2 implements Runnable {                             //Server message reader
        @Override
        public void run() {
            while (true) {
                try {
                    final String message = input.readLine();
                    if (message!= null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                receiveValue(message);
                            }
                        });
                    } else {
                        Thread1 = new Thread(new FourthActivity.Thread1());
                        Thread1.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class Thread3 implements Runnable {                             //Phone message reader / sender
        private String message;
        Thread3(String message) {
            this.message = message;
        }
        @Override
        public void run() {
            output.write(message);
            output.flush();
        }
    }

    private void getProgressOne(){
        progressOne=String.valueOf(seekBarOne.getProgress());
    }

    private  void getProgressTwo(){
        progressTwo=String.valueOf(seekBarTwo.getProgress());
    }

    private  void receiveValue(String message){

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
            switchOne.setChecked(true);
        }else{
            switchOne.setChecked(false);
        }

    }

}
