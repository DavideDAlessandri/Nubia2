package com.example.nubia_multipage;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class EightActivity extends AppCompatActivity {

    Boolean running=true;
    public static Activity fa8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.eight_ly_gripper);

        fa8 = this; //assign name activity 8
    }

    @Override
    protected  void onResume(){
        super.onResume();

        MyService.currentPage=8;

        MainActivity.screenSaverOn=false;
        MainActivity.startStatus=false;                                                             //reset start status (stop when change activity)
        running=true;

        if(MyService.connectStatus){                                                                //If tcp connected
            //new Thread(new SecondActivity.Thread2("PGR02")).start();                                // send current page name to server
        }
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
