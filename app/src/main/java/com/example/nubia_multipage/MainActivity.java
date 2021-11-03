package com.example.nubia_multipage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {

    private Button secondPage;
    private Button thirdPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        secondPage=findViewById(R.id.secondPage);
        thirdPage = findViewById(R.id.thirdPage);

        secondPage.setOnClickListener(new View.OnClickListener() {      //Click change page
            @Override
            public void onClick(View v) {
                changeActivityTwo();
            }
        });

        thirdPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                               //Click connect server
                changeActivityThree();
            }
        });

    }


    private void changeActivityTwo(){                                      //Click change page
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

    private void changeActivityThree(){                                      //Click change page
        Intent intent = new Intent(this,ThirdActivity.class);
        startActivity(intent);
    }

}