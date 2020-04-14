package com.lec.android.a002_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint1);


        int ss = 100;
        String tt = "hello 히히";
        // 저장이랑 commit 이랑 개념이 다르다!!
        // 변경 내용은 파란색으로 표시된다!!

        char ch = 'a';
        short s = 200;
    }
}
