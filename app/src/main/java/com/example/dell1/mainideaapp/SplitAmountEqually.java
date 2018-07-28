package com.example.dell1.mainideaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.StrictMath.abs;

public class SplitAmountEqually extends AppCompatActivity{

    Double totalsum;
    GroupDao groupDao;
    String groupDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclicksplit);
        final Intent intent=getIntent();
        totalsum=intent.getDoubleExtra("total sum",0.0);
        groupDate=intent.getStringExtra("grpDate");

        groupDao=MyAppApplication.getMyAppDatabase().getGroupDao();
        final MyGroups clickedGrp=groupDao.getgroupWithId(groupDate);
        groupDao.updateGroup(clickedGrp);
        final SaveBill saveBill=new SaveBill();
        MyAppApplication.getMyAppDatabase().getSaveBillDao().updateSavedBill(saveBill);

        TextView textView=findViewById(R.id.tvdisplayTotalAmount);
        RecyclerView recyclerView1=findViewById(R.id.rv_splitEqually);
        textView.setText(String.valueOf(totalsum));
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getBaseContext());


        SplitEquallyAdapter splitEquallyAdapter =new SplitEquallyAdapter(getBaseContext(),totalsum,clickedGrp);
        recyclerView1.setAdapter(splitEquallyAdapter);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        Button button=findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                Intent intent1=new Intent(SplitAmountEqually.this,FinalResult.class);
                intent1.putExtra("clickedGrpDate",groupDate);
                intent1.putExtra("totalsum",totalsum);
                startActivity(intent1);
            }
        });
//        RecyclerView recyclerView2=findViewById(R.id.rv_settledebts);
//        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getBaseContext());
//        recyclerView2.setLayoutManager(linearLayoutManager2);
//        SettleDebtsAdapter settleDebtsAdapter=new SettleDebtsAdapter(getBaseContext(),totalsum,clickedGrp);
//        recyclerView2.setAdapter(settleDebtsAdapter);
    }
//
}
