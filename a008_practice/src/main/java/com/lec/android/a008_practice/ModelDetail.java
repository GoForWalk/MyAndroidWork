package com.lec.android.a008_practice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class ModelDetail extends AppCompatActivity implements Serializable {

    EditText etNameDetail, etAgeDetail, etAddressDetail, etPhone, etBirthday;
    RadioGroup rg;
    Button btnSave, btnReturn;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_detail);

        etNameDetail = findViewById(R.id.etNameDetail);
        etAddressDetail = findViewById(R.id.etAddressDetail);
        etAgeDetail = findViewById(R.id.etAgeDetail);
        etPhone = findViewById(R.id.etPhone);
        etBirthday = findViewById(R.id.etBirthday);

        rg = findViewById(R.id.rg);

        btnSave = findViewById(R.id.btnSave);
        btnReturn = findViewById(R.id.btnReturn);

        Intent intent = getIntent();
        // TODO 인텐트 받기

        // TODO 저장하면 수정하도록 하기!!



    }



}
