package com.gmail.atcgsp912.ver20042101.blockgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class Intro extends AppCompatActivity {

    //초기화면
    // 3초동안 보이고, 다음화면 (Main) 으로 넘어가기..

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        // handler

        Handler mHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent); // 화면 젼환
                finish(); // intro 화면은 종료

            }
        };
        mHandler.sendEmptyMessageDelayed(1, 3000);



    }// end onCreate()

}// end Activity
