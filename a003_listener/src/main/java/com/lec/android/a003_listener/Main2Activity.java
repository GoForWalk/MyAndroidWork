package com.lec.android.a003_listener;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener {


//    EditText et = (EditText)findViewById(R.id.etcalculaor);
    EditText et;
    TextView calculResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et = findViewById(R.id.etcalculaor);
        calculResult = findViewById(R.id.Result);


        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);


    class MyCalculator implements View.OnClickListener{ // 숫자 입력!!

        @Override
        public void onClick(View v) {
            int inputNum = Integer.parseInt((String)((Button)v).getText());
            String msg = String.format("%d", inputNum);
            et.setText(et.getText().append(msg));
        }
    }

        btn0.setOnClickListener(new MyCalculator());
        btn1.setOnClickListener(new MyCalculator());
        btn2.setOnClickListener(new MyCalculator());
        btn3.setOnClickListener(new MyCalculator());
        btn4.setOnClickListener(new MyCalculator());
        btn5.setOnClickListener(new MyCalculator());
        btn6.setOnClickListener(new MyCalculator());
        btn7.setOnClickListener(new MyCalculator());
        btn8.setOnClickListener(new MyCalculator());
        btn9.setOnClickListener(new MyCalculator());

        Button btnDiv = findViewById(R.id.btnDiv);
        Button btnMulti = findViewById(R.id.btnMulti);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnPlus = findViewById(R.id.btnPlus);

    class MyCalculator2 implements View.OnClickListener{ // 부호 입력!!


        @Override
        public void onClick(View v) {
            String inputSign = (String)((Button)v).getText();
            et.setText(et.getText().append(inputSign));
        }
    }
        btnDiv.setOnClickListener(new MyCalculator2());
        btnMulti.setOnClickListener(new MyCalculator2());
        btnMinus.setOnClickListener(new MyCalculator2());
        btnPlus.setOnClickListener(new MyCalculator2());

    Button btnReset = findViewById(R.id.btnReset); // 삭제 버튼 2
    btnReset.setOnClickListener(this);

    Button btnEqual = findViewById(R.id.btnEqual);
    btnEqual.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v) {

        try {
            LinkedList<Double> numList = new LinkedList<Double>(); //숫자관련
            LinkedList<Character> chList = new LinkedList<Character>(); //연산자 관련

            String calculation = et.getText().toString().
                    replace("X", "*").replace("÷", "/").trim();

            String num = "";

            for (int i = 0; i < calculation.length(); i++) {
                char ch = calculation.charAt(i);

                if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    numList.add(Double.parseDouble(num)); //숫자로 바꿔서 숫자배열에 추가
                    chList.add(ch); //연산자를 연산자배열에 추가
                    num = ""; //임시로 저장된 숫자를 비워준다
                    continue; //제일 가까운 명령문으로 이동
                }
                num += ch;
            }
            numList.add(Double.parseDouble(num)); //마지막 숫자

            while (!chList.isEmpty()) { //연산자배열이 빌 때까지
                double prevNum = numList.poll(); //poll : 앞부터 완전히 뺀다
                double nextNum = numList.poll();
                char op = chList.poll();

                if (op == '+') {
                    numList.addFirst(prevNum + nextNum); //addFirst 배열 제일 앞에 넣는다
                } else if (op == '-') {
                    numList.addFirst(prevNum - nextNum);
                } else if (op == '*') {
                    numList.addFirst(prevNum * nextNum);
                } else if (op == '/') {
                    numList.addFirst(prevNum / nextNum);
                }
            }
            calculResult.setText(String.format("%f", numList.poll()));
        }catch (NumberFormatException ex){
            calculResult.setText("수식이 틀렸습니다.");

        }





        }
    });
    } // end onCreate()

    @Override // 삭제 (1)
    public void onClick(View v){
        et.setText("");
        calculResult.setText("0");
    }


}// end Activity
