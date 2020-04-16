package com.lec.android.a006_widget2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Main3Activity extends AppCompatActivity {

    TextView tvResult;
    SeekBar seekBar;

    int value = 0;
    int add = 2;

    Handler handler = new Handler();

    boolean isTracking;

    ToggleButton toggleButton1;
    boolean isStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvResult = findViewById(R.id.tvResult);
        seekBar = findViewById(R.id.seekBar);



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            // 값의 변화가 생겼을때 콜백(호출)
            // progress : 진행값 0 ~ Max
            // fromUser : 사용자에 의한 진행값 변화인 경우 true / 이외는 false
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvResult.setText("onProgressChanged: " + progress + "(" + fromUser +")");
            }

            // tracking 시작할때 콜백
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "트래킹 시작" ,Toast.LENGTH_SHORT).show();
                isTracking = true;
            }

            // tracking 끝날 때 콜백
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "트래킹 종료" ,Toast.LENGTH_SHORT).show();
                isTracking = false;
            }
        });

        toggleButton1 = findViewById(R.id.toggleButton2);

        toggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isStop = true;
                } else {
                    isStop = false;
                }
            }
        });


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int max = seekBar.getMax();
                for (; ; ) {

                    if (isStop) {

                        if (!isTracking) {// 트래킹 중이 아닐 때만 SeekBar 이동
                            value = seekBar.getProgress() + add;

                            if (value > max || value < 0) {
                                add = -add;
                            }

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    seekBar.setProgress(value);
                                }
                            });


                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    } // end for
                }
            }
        });
        t.start();

    } // end onCreate()


}// end Activity
