package com.example.nubia_multipage;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SixthActivity extends AppCompatActivity {

    TextView tvMessage;
    Button backButton;
    Boolean running=true;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.sixth_layout);

        tvMessage=findViewById(R.id.tvMessages);
        backButton=findViewById(R.id.backButton6);

        tvMessage.setText("avvio");
        tvMessage.append("server2\n");
        tvMessage.append("server1\n");
        tvMessage.append("server1\n");
        tvMessage.append("server1\n");
        tvMessage.append("server1\n");
        tvMessage.append("server1\n");
        tvMessage.append("server3\n");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected  void onResume(){
        super.onResume();

        running=true;
        MyService.messageToActivity="null";
        new Thread(new SixthActivity.Thread1()).start();


    }

    class Thread1 implements Runnable {                             //Server message reader
        @Override
        public void run() {

            if (!running) return;                                   //Exit thread
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    returnThread(MyService.messageToActivity);

                }
            });

        }
    }

    private void returnThread(String message){

        if(message.equals("null")){
            new Thread(new SixthActivity.Thread1()).start();

        }else{

            tvMessage.setText("server: " + message + "\n");
            new Thread(new SixthActivity.Thread1()).start();
        }

    }

    @Override
    protected void onStop() {

        super.onStop();
        running=false;
    }


}
