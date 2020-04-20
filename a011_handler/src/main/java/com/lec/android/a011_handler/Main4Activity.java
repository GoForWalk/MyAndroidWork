package com.lec.android.a011_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

// TODO
// value1
// 1 ~ 10 까지 1 초 단위로 증가시키기
// 10초에 도달하면 멈추어서 Toast 띄우기
// Message 사용

// value2
// 1 ~ 20 까지 1초 단위로 증가시키기
// 20 초에 도달하면 멈춰서 Toast 띄우기
// Handler 사용

public class Main4Activity extends AppCompatActivity {

    TextView tvResult1 , tvResult2,tvResult3, tvResult4, tvResult5;
    Handler mHandler = new Handler();
    Handler mHandler2, mHandler3;

    int backValue1 = 0;
    int backValue2 = 0;
    int value3 = 0, value4 = 0, value5 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        tvResult1 = findViewById(R.id.tvResult1);
        tvResult2 = findViewById(R.id.tvResult2);
        tvResult3 = findViewById(R.id.tvResult3);
        tvResult4 = findViewById(R.id.tvResult4);
        tvResult5 = findViewById(R.id.tvResult5);

    BackThread1 thread1 = new BackThread1();
    thread1.setDaemon(true);
    thread1.start();



    // 방법3 : 메소드 내부에서 생성
        mHandler3 = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                value3 ++;
                tvResult3.setText("Value3 = " + value3);

                if(value3 < 5){
                    mHandler3.sendEmptyMessageDelayed(0, 1000);

                } else {
                    Toast.makeText(getApplicationContext(), "Value3 종료", Toast.LENGTH_SHORT);
                }

            }
        };
        mHandler3.sendEmptyMessage(0); // 시작!!

        // 방법 #4
        // 핸들러를 사용하지 않고도 일정시간마다 (혹은 후에) 코스를 수행할수 있도록
        // CountDownTimer 클래스가 제공된다.
        // '총시간'  과 '인터벌(간격)' 을 주면 매 간격마다 onTick 메소드를 수행한다.

        new CountDownTimer(15*1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) { // 매 간격마다 수행하는 코드
                value4++;
                tvResult4.setText("Value4 = " + value4);
            }

            @Override
            public void onFinish() { // 종료시 수행하는 코드
                Toast.makeText(getApplicationContext(), "Value4 종료", Toast.LENGTH_LONG).show();
            }
        }.start(); // 타이머 시작


    }// end onCreate()




    class BackThread1 extends Thread{
        @Override
        public void run() {
            for(;;){
                backValue1++;
                mHandler.sendEmptyMessage(1 );

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(backValue1 == 10){
                    Toast.makeText(getApplicationContext(), "BackThread1 STOP", Toast.LENGTH_SHORT).show();
                    break;
                }
            }// end for

        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            for(;;){
                backValue2++;
                tvResult2.setText(msg.arg1);
            }
        }
    };



}// end Activity
