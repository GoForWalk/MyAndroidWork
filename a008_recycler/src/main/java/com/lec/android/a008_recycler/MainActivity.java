package com.lec.android.a008_recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    PhonebookAdapter adapter; // Adapter 객체
    RecyclerView rv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);

        // RecycleView 를 사용하기 위해서는 LayoutManager 지정해주어야 한다.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rv.setLayoutManager(layoutManager);

        // Adaptor 객체 생성
        adapter = new PhonebookAdapter();


        initAdapter(adapter);


        rv.setAdapter(adapter); // recyclerView 에 Adaptor 장착!!


    } // end onCreate()

    // 샘플데이터 가져오기
    protected void initAdapter(PhonebookAdapter adapter){
        // 몇개만 생성
        for(int i = 0; i < 10; i++){
            int idx = D.next();
            adapter.addItem(new Phonebook(D.FACEID[idx], D.NAME[idx], D.PHONE[idx], D.EMAIL[idx]));
        }
    }


} // end Activity
