package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    EditText ed1;
    EditText ed2;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnDiv = findViewById(R.id.btnDiv);
        Button btnMulti = findViewById(R.id.btnMul);
        tvResult = findViewById(R.id.tvResult);
        ed1 = findViewById(R.id.op1);
        ed2 = findViewById(R.id.op2);

        ed1.setOnFocusChangeListener(new View.OnFocusChangeListener(){


            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((EditText)v).setBackgroundColor(Color.YELLOW);
                } else {
                    ((EditText)v).setBackgroundColor(Color.parseColor("#00000000"));
                }
            }
        });

        ed2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((EditText)v).setBackgroundColor(Color.YELLOW);
                } else {
                    ((EditText)v).setBackgroundColor(Color.parseColor("#00000000"));
                }

            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               double result = Double.parseDouble(ed1.getText().toString()) + Double.parseDouble(ed2.getText().toString());
               tvResult.setText(String.format("%.1f", result));
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = Double.parseDouble(ed1.getText().toString()) / Double.parseDouble(ed2.getText().toString());
                tvResult.setText(String.format("%.1f", result));
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = Double.parseDouble(ed1.getText().toString()) - Double.parseDouble(ed2.getText().toString());
                tvResult.setText(String.format("%.1f", result));
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = Double.parseDouble(ed1.getText().toString()) * Double.parseDouble(ed2.getText().toString());
                tvResult.setText(String.format("%.1f", result));
            }
        });


    } //onCreate() end


}// Activity end
