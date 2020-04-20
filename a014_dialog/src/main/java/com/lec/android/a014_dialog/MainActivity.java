package com.lec.android.a014_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 대화상자 객체
    Dialog dlg1;
    ImageView ivDlgBanner;
    Button btnDlgEvent;

    Dialog dlg2;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 액티비티 레이아웃 파일

        // Dialog 클래스로 다이얼로그 객체 생성 및 세팅
        dlg1 = new Dialog(this); // 다이얼로그 객체 생성
        dlg1.setContentView(R.layout.dialog_layout11); // 다이얼로그 화면 등록

        // Dialog 안의 View 객체들 얻어오기
        ivDlgBanner = dlg1.findViewById(R.id.ivDlgBanner); // 다이얼로그 안에 있는 아이디로 find
        btnDlgEvent = dlg1.findViewById(R.id.btnDlgEvent);

        btnDlgEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivDlgBanner.setImageResource(R.drawable.face01);
                Toast.makeText(getApplicationContext(), "다이얼로그 버튼을 눌럿어요!!", Toast.LENGTH_SHORT).show();
            }
        });

        // Activity 에 Dialog 등록하기
        dlg1.setOwnerActivity(MainActivity.this);
        dlg1.setCanceledOnTouchOutside(true); // 다이얼로그 바깥 영역 클릭시 (혹은 back 버튼 클릭시) hide 상태가 된다. ( 사라지는거 아님!)
                                                // 종료할것인지 여부, true : 종료됨, false : 종료안댐.






    }


    public void showDialog1(View v){
        dlg1.show(); // 다이얼로그 띄우기

    }

    public void showDialog2(View v){
        // TODO
    }
}
