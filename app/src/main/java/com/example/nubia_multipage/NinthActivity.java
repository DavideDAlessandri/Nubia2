package com.example.nubia_multipage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class NinthActivity extends AppCompatActivity {

    Boolean running=true;
    public static Activity fa9;
    View layout;
    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.ninth_ly_monitor_two);

        layout=findViewById(R.id.layout9);

        fa9 = this; //assign name activity 9

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        x1=event.getX();
                        y1=event.getY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        x2=event.getX();
                        y2=event.getY();
                        if(x2>x1){
                            changeActivityTen();
                        }
                        break;
                }

                return false;
            }
        });

    }

    @Override
    protected  void onResume(){
        super.onResume();

        MyService.currentPage=9;

        MainActivity.screenSaverOn=false;
        MainActivity.startStatus=false;                                                             //reset start status (stop when change activity)
        running=true;
        MyService.messageToActivity="null";

        //if(MyService.connectStatus){                                                                //if tcp connected
        //    new Thread(new SeventhActivity.Thread2("PGR07")).start();                      // send current page name to server
        //    new Thread(new SeventhActivity.Thread1()).start();
        //}


    }

    private void changeActivityTen() {
        Intent Intent = new Intent(this, TenthActivity.class);
        startActivity(Intent);
        finish();
    }

    @Override
    protected void onStop() {

        super.onStop();
        running=false;                                                                              //Stop thread 1
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_down);                     //animation out
    }
}
