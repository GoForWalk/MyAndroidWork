package com.lec.android.a018_touch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    TextView tvResult;

    // 3개 까지의 멀티터치를 다루기 위한 배열
    int id [] = new int[3];
    int x [] = new int[3];
    int y [] = new int[3];
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvResult = findViewById(R.id.tvResult);

    }// end onCreate()

    // 액티비티에서 발생한 터치 이벤트 처리
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // 현재 터치 발생한 포인트 개수를 얻어온다.
        int pointCount = event.getPointerCount();
        if(pointCount > 3) pointCount = 3; // 4개 이상의 포인트 터치했더라도 3개까지만 처리.

        switch (event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN: // 한개 포인트에 대한 DOWN 이 발생했을 때
                id[0] = event.getPointerId(0); // 터치한 순간에 부여하는 포인트 고유번호
                x[0] = (int)(event.getX());
                y[0] = (int)(event.getY());
                result = "싱글터치 : \n";
                result = "(" + x[0] + ", " + y[0] + ")";
                break;

                // 멀티터치
            case MotionEvent.ACTION_POINTER_DOWN: // 두개 이상의 포인트에 대한 DOWN이 발생했을 때
                result = "멀티터치 : \n";
                for(int i = 0; i < pointCount; i++){
                    id[i] = event.getPointerId(i);
                    x[i] = (int)(event.getX(i));
                    y[i] = (int)(event.getY(i));
                    result += "id[" + id[i] + "] (" + x[i] + ", " + y[i] + ")\n";
                }
                break;

            case MotionEvent.ACTION_MOVE:
                result = "멀티터치 move : \n";
                for(int i = 0; i < pointCount; i++){
                    id[i] = event.getPointerId(i);
                    x[i] = (int)(event.getX(i));
                    y[i] = (int)(event.getY(i));
                    result += "id[" + id[i] + "] (" + x[i] + ", " + y[i] + ")\n";
                }

                break;

            case  MotionEvent.ACTION_UP:
                //TODO

                break;


        } // end switch

        tvResult.setText(result);

        return super.onTouchEvent(event);
    }
}// end Activity