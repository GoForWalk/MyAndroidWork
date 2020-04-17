package com.lec.android.a007_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


// 화면이 없는 액티비티 생성 가능!!
public class CalcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int num1 = intent.getIntExtra("num1", 0);
        int num2 = intent.getIntExtra("num2", 0);

        intent.putExtra("plus", num1 + num2); // 풋!! 결과값 다시 메인으로 돌려줌!!
        intent.putExtra("minus", num1 - num2);

        // 호출한 화면에 값 되돌려 주기
        setResult(RESULT_OK);

        finish(); // onDestroy() 와 동일


    }
}
