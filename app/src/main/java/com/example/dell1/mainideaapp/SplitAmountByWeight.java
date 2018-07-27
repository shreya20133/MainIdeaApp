package com.example.dell1.mainideaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class SplitAmountByWeight extends AppCompatActivity{

    Double totalsum;
    GroupDao groupDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclicksplit);
        Intent intent=getIntent();
        totalsum=intent.getDoubleExtra("total sum",0.0);
        String groupDate=intent.getStringExtra("grpDate");

        groupDao=MyAppApplication.getMyAppDatabase().getGroupDao();
        MyGroups clickedGrp=groupDao.getgroupWithId(groupDate);

        TextView textView=findViewById(R.id.tvdisplayTotalAmount);
        RecyclerView recyclerView1=findViewById(R.id.rv_splitByWeight);

        textView.setText(String.valueOf(totalsum));
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getBaseContext());
        recyclerView1.setLayoutManager(linearLayoutManager1);
        SplitByWeightAdapter splitByWeightAdapter =new SplitByWeightAdapter(getBaseContext(),totalsum,clickedGrp);
        recyclerView1.setAdapter(splitByWeightAdapter);


//        RecyclerView recyclerView2=findViewById(R.id.rv_settledebts);
//        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getBaseContext());
//        recyclerView2.setLayoutManager(linearLayoutManager2);
//        SettleDebtsAdapter settleDebtsAdapter=new SettleDebtsAdapter(getBaseContext(),totalsum,clickedGrp);
//        recyclerView2.setAdapter(settleDebtsAdapter);
    }
}
