package com.lec.android.a005_image;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 폰의 저장장치에 있는 사진을 ImageView 에 띄우기
 *    1. 권한 획득
 *        - 메니페스트
 *        - '위험권한'요청  (Android 6.0 / API23 이상)
 *         https://developer.android.com/guide/topics/security/permissions?hl=ko  (위험권한및 위험권한 목록들)
 *            https://developer.android.com/training/permissions/requesting?hl=ko#java
 *
 *            설치 앱별 '권한' 확인 가능하다 :
 *                픽셀2 폰의 경우 '앱' 아이콘 롱클릭후 App Info 확인
 *
 *    2. 이미지 경로
 *    3. Bitmap 사용하여 ImageView 에 띄우기
 */

public class Main3Activity extends AppCompatActivity {

    ImageView ivPhoto;
    TextView tvPath;
    Button btnAction;


    // 이미지 경로 알아내기 ( 제조사, 모델마다 다를 수 있다.)
    // 픽셀폰 : 갤러리 이미지 보기 -> 하단의 i 버튼
    // 삼성폰 : 갤러리 이미지 롱 클릭후 상세정보..
    private String imgPath ="/storage/emulated/0/DCIM/Camera/france/png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ivPhoto = findViewById(R.id.ivPhoto);
        btnAction = findViewById(R.id.btnAction);
        tvPath = findViewById(R.id.tvPath);





    } // onCreate() end

    public void drawPhoto(){
        // ImageView <- Bitmap <- 경로
        Bitmap bm = BitmapFactory.decodeFile(imgPath);
        ivPhoto.setImageBitmap(bm);
        tvPath.setText(imgPath);
        }



} // Activity end
