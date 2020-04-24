package com.lec.android.a019_graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);

        setContentView(new MyView2(this));

    }// end onCreate()


}// end Activity


class MyView2 extends View{
    int x = 100, y = 100;

    public MyView2(Context context) {

        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(40);

        canvas.drawRect(-100, -100, 100, 100, paint);

        paint.setColor(Color.BLUE);
        canvas.drawText("글씨", 300 , 300, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                x = (int)event.getX();
                y = (int)event.getY();
                invalidate(); // 화면을 다시 그린다 --> onDraw() 를 재호출
        }

        return true;
    }
}
