package com.example.nubia_multipage;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FifthActivity extends AppCompatActivity {

    ProgressBar progressBarOne, progressBarTwo, progressBarThree, progressBarFour, progressBarFive, progressBarSix;
    TextView speedText;
    TextView progressTextOne, progressTextTwo, progressTextThree, progressTextFour, progressTextFive, progressTextSix;
    CheckBox backBox;
    CheckBox checkBoxX, checkBoxY, checkBoxZ, checkBoxA, checkBoxB, checkBoxC, checkBoxN;

    Thread Thread1 = null;
    String SERVER_IP = "192.168.1.2";
    int SERVER_PORT = 8080;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.fifth_layout);

        progressBarOne=findViewById(R.id.progressBarOne5);
        progressBarTwo=findViewById(R.id.progressBarTwo5);
        progressBarThree=findViewById(R.id.progressBarThree5);
        progressBarFour=findViewById(R.id.progressBarFour5);
        progressBarFive=findViewById(R.id.progressBarFive5);
        progressBarSix=findViewById(R.id.progressBarSix5);

        progressTextOne=findViewById(R.id.progressTextOne5);
        progressTextTwo=findViewById(R.id.progressTextTwo5);
        progressTextThree=findViewById(R.id.progressTextThree5);
        progressTextFour=findViewById(R.id.progressTextFour5);
        progressTextFive=findViewById(R.id.progressTextFive5);
        progressTextSix=findViewById(R.id.progressTextSix5);

        checkBoxX=findViewById(R.id.checkBoxX);
        checkBoxY=findViewById(R.id.checkBoxY);
        checkBoxZ=findViewById(R.id.checkBoxZ);
        checkBoxA=findViewById(R.id.checkBoxA);
        checkBoxB=findViewById(R.id.checkBoxB);
        checkBoxC=findViewById(R.id.checkBoxC);
        checkBoxN=findViewById(R.id.checkBoxN);

        speedText=findViewById(R.id.speedText);
        backBox=findViewById(R.id.backBox);

        speedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityFour();
            }
        });

        backBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        checkBoxX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBoxX.isChecked()){
                    new Thread(new FifthActivity.Thread3("X=1")).start();
                }else {
                    new Thread(new FifthActivity.Thread3("X=0")).start();
                }

            }
        });

        checkBoxY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxY.isChecked()){
                    new Thread(new FifthActivity.Thread3("Y=1")).start();
                }else {
                    new Thread(new FifthActivity.Thread3("Y=0")).start();
                }
            }
        });

        checkBoxZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxZ.isChecked()){
                    new Thread(new FifthActivity.Thread3("Z=1")).start();
                }else {
                    new Thread(new FifthActivity.Thread3("Z=0")).start();
                }
            }
        });

        checkBoxA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxA.isChecked()){
                    new Thread(new FifthActivity.Thread3("A=1")).start();
                }else {
                    new Thread(new FifthActivity.Thread3("A=0")).start();
                }
            }
        });

        checkBoxB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxB.isChecked()){
                    new Thread(new FifthActivity.Thread3("B=1")).start();
                }else {
                    new Thread(new FifthActivity.Thread3("B=0")).start();
                }
            }
        });

        checkBoxC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxC.isChecked()){
                    new Thread(new FifthActivity.Thread3("C=1")).start();
                }else {
                    new Thread(new FifthActivity.Thread3("C=0")).start();
                }
            }
        });

        checkBoxN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxN.isChecked()){
                    new Thread(new FifthActivity.Thread3("N=1")).start();
                }else {
                    new Thread(new FifthActivity.Thread3("N=0")).start();
                }
            }
        });

        Thread1 = new Thread(new FifthActivity.Thread1());
        Thread1.start();

    }


    private void changeActivityFour(){                                      //Click change page
        Intent intent = new Intent(this,FourthActivity.class);
        startActivity(intent);
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
                new Thread(new FifthActivity.Thread2()).start();
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
                        Thread1 = new Thread(new FifthActivity.Thread1());
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


    private  void receiveValue(String message){

        String val1=message.substring(0,3);             //get progressbar 1 value
        String val2=message.substring(3,6);             //get progressbar 2 value
        String val3=message.substring(6,9);
        String val4=message.substring(9,12);
        String val5=message.substring(12,15);
        String val6=message.substring(15,18);

        String val7=message.substring(18,19);           //get checkBox X value
        String val8=message.substring(19,20);           //get checkBox Y value
        String val9=message.substring(20,21);           //get checkBox Z value
        String val10=message.substring(21,22);          //get checkBox A value
        String val11=message.substring(22,23);          //get checkBox B value
        String val12=message.substring(23,24);          //get checkBox C value
        //String val13=message.substring(24,25);          //get checkBox N value

        int number1 = Integer.parseInt(val1);           //set progressbar 1 value
        progressBarOne.setProgress(number1);
        progressTextOne.setText(number1+" N");

        int number2 = Integer.parseInt(val2);           //set progressbar 2 value
        progressBarTwo.setProgress(number2);
        progressTextTwo.setText(number2+" N");

        int number3 = Integer.parseInt(val3);
        progressBarThree.setProgress(number3);
        progressTextThree.setText(number3+" N");

        int number4 = Integer.parseInt(val4);
        progressBarFour.setProgress(number4);
        progressTextFour.setText(number4+" Nm");

        int number5 = Integer.parseInt(val5);
        progressBarFive.setProgress(number5);
        progressTextFive.setText(number5+" Nm");

        int number6 = Integer.parseInt(val6);
        progressBarSix.setProgress(number6);
        progressTextSix.setText(number6+" Nm");

        if(val7.equals("1")){
            checkBoxX.setChecked(true);                 //set check box
        }else{
            checkBoxX.setChecked(false);
        }

        if(val8.equals("1")){
            checkBoxY.setChecked(true);
        }else{
            checkBoxY.setChecked(false);
        }

        if(val9.equals("1")){
            checkBoxZ.setChecked(true);
        }else{
            checkBoxZ.setChecked(false);
        }

        if(val10.equals("1")){
            checkBoxA.setChecked(true);
        }else{
            checkBoxA.setChecked(false);
        }

        if(val11.equals("1")){
            checkBoxB.setChecked(true);
        }else{
            checkBoxB.setChecked(false);
        }

        if(val12.equals("1")){
            checkBoxC.setChecked(true);
        }else{
            checkBoxC.setChecked(false);
        }


    }


}
