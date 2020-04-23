package com.lec.android.a010_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class Main5Activity extends AppCompatActivity {

    EditText etInput;
    String sfName = "myFile";

    // sharedPreference
    // key - Value 쌍으로 데이터 저장한다!!
    // 작은 데이터들 (세팅값들) 저장 용도로 활용
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        etInput = findViewById(R.id.etInput);

        // 저장되어 있는 값을 꺼내서 보여주기
        SharedPreferences sf = getSharedPreferences(sfName, MODE_PRIVATE);
        String str = sf.getString("name", ""); // 키 값으로 꺼냄
        String xx = sf.getString("xx", "ABC");
        String yy = sf.getString("yy", "XYZ");

        etInput.setText(str);
        Log.d("myapp", str + " - " + xx + " - " + yy);

    } // end onCreate()

    @Override
    protected void onPause() {// 종료되기 전에 저장한다.
        super.onPause();

        // Activity 가 종료되기 전에 저장
        SharedPreferences sf = getSharedPreferences(sfName, MODE_PRIVATE);

        // 저장하려면 editor 객체 필요
        SharedPreferences.Editor editor = sf.edit(); // 저장하려면 Editor 객체 필요

        String str = etInput.getText().toString(); // 사용자가 입력한 값

        editor.putString("name", str);
        editor.putString("xx", "가나다");
        editor.commit(); // SharedPreferences 에 저장!! -> 파일의 형태로 저장
    } // end onPause()


} // end Activity
