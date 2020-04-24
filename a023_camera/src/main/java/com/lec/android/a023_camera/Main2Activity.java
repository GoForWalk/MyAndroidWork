package com.lec.android.a023_camera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/*  카메라 화면 보여주기 --> SurfaceView 사용

                                   1.프리뷰설정
                                    --->
   SurfaceView <-->  SurfaceHolder <---   카메라   2.프리뷰 시작
              3. 프리뷰표시        3. 프리뷰 디스플레이

   SuffaceView 는 SurfaceHolder 에 의해 제어되는 모습
               - serPreviewDisplay() 로 미리보기 설정해주어야 함

   초기화 작업후 카메라객체의 startPreview() 호출 --> 카메라 영상이 SurfaveView 로 보이게 된다
   주의!: Surface 타입은 반드시 SURFACE_TYPE_PUSH_BUFFERS)

   SurfaceView 가  SURFACE_TYPE_PUSH_BUFFERS 타입인 경우, 카메라 보여주기 외에 다른 그림 못 그림
   그 위에 다른 그림 (아이콤, 마커, 증강현실..) 그리려면 별도의 레이아웃을 위에 포개야 한다

*/

public class Main2Activity extends AppCompatActivity {

    String[] permissions = {Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    final int REQUEST_CODE = 101;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 권한 획득 하기
        if(Build.VERSION.SDK_INT >= 23 ){
            if(checkSelfPermission(String.valueOf(permissions)) == PackageManager.PERMISSION_DENIED){
                requestPermissions(permissions, REQUEST_CODE);  // 권한 요청하기
            }
        }








    }// end onCreate()

    // SurfaceView 상속

    private class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback{


        private SurfaceHolder mHolder;
        private Camera camera = null;

        public CameraSurfaceView(Context context) {
            super(context);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    } // end CameraSurfaceView

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length <= 0){
                    Toast.makeText(this, "권한흭득 실패", Toast.LENGTH_SHORT).show();

                    // 앱 종료
                    // onDestroy , finish
                    return;
                }

                String result = "";
                for(int i = 0; i < grantResults.length; i++){
                    if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                        result += "권한흭득 성공!" + permissions[i] + "\n";

                    } else {
                        result += "권한흭득 실패: " + permissions[i] + "\n";
                    }

                } // end for

                Toast.makeText(this, result, Toast.LENGTH_LONG);
                Log.d("myapp", result);
                break;

        }
    }


}// end Activity
