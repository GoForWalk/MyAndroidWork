package com.lec.android.a014_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    final int DIALOG_DATE = 1;
    final int DIALOG_TIME = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button btnDatePicker = findViewById(R.id.btnDatePicker);
        Button btnTimePicker = findViewById(R.id.btnTimePicker);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showPickerDialog(DIALOG_DATE); // 날짜 설정 다이얼로그 띄우기
            }
        });
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showPickerDialog(DIALOG_TIME);
            }
        });
    } // end of onCreate

    protected Dialog showPickerDialog(int id){

        switch (id){
            case DIALOG_DATE:
                DatePickerDialog dpd = new DatePickerDialog(
                        this, // 현재화면의 제어권자. (this = Activity)

                         // 날짜 설정후 Dialog 빠져나올때 호출되는 콜백
                         new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(getApplicationContext(), year + "년 " + (month + 1) + "월 " + dayOfMonth + "일 선택",
                                Toast.LENGTH_SHORT).show(); // 월은 0 부터 시작!!
                    }
                },
                2020, 4, 21 // 기본값 연월일
                );

                dpd.show();
                return dpd;

            case DIALOG_TIME:
                TimePickerDialog tpd = new TimePickerDialog(
                        this, // 현재화면 제어권자

                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Toast.makeText(getApplicationContext(),
                                        hourOfDay + "시 " + minute + "분 선택", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        },
                        17, 21, // 기본값 시 , 분
                        false // 24시간제 : true , 12 시간제 false(오전/ 오후) 항복 표시

                );
                tpd.show();
                return tpd;

        }

        return null;
    } // end showPickerDialog()



} // end Activity
