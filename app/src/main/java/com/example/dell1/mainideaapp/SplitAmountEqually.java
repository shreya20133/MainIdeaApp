package com.example.dell1.mainideaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class SplitAmountEqually extends AppCompatActivity{

    Double totalsum;
    GroupDao groupDao;
    GroupMemberDao groupMemberDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclicksplit);
        Intent intent=getIntent();
        totalsum=intent.getDoubleExtra("total sum",0.0);
        String groupDate=intent.getStringExtra("grpDate");

        groupDao=MyAppApplication.getMyAppDatabase().getGroupDao();
        MyGroups clickedGrp=groupDao.getgroupWithId(groupDate);

        groupMemberDao=MyAppApplication.getMyAppDatabase().getGroupMemberDao();

        TextView textView=findViewById(R.id.tvdisplayTotalAmount);
        RecyclerView recyclerView1=findViewById(R.id.rv_splitEqually);

        textView.setText(String.valueOf(totalsum));
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getBaseContext());
        recyclerView1.setLayoutManager(linearLayoutManager1);
        SplitEquallyAdapter splitEquallyAdapter =new SplitEquallyAdapter(getBaseContext(),totalsum,clickedGrp);
        for(GroupMembers g: clickedGrp.getGroupMembersArrayList()){
            groupMemberDao.updateGroupMember(g);
        }
        groupDao.updateGroup(clickedGrp);
        recyclerView1.setAdapter(splitEquallyAdapter);


        Map<String,Double> hashMap=new HashMap<>();
        for(int i=0;i<clickedGrp.getGroupMembersArrayList().size();i++){
            String currentGrpMemberName=clickedGrp.getGroupMembersArrayList().get(i).getName();
            Double currentGrpMemberBalance=clickedGrp.getGroupMembersArrayList().get(i).getPaidAmount() - clickedGrp.getGroupMembersArrayList().get(i).getAmountSplit();
            hashMap.put(currentGrpMemberName,currentGrpMemberBalance);
        }
        Set<Map.Entry<String,Double>> mapEntries = hashMap.entrySet();
        List<Map.Entry<String,Double>> aList = new LinkedList<>(mapEntries);
        Collections.sort(aList, new Comparator<Map.Entry<String,Double>>() {

            @Override
            public int compare(Map.Entry<String, Double> ele1,
                               Map.Entry<String, Double> ele2) {

                return ele1.getValue().compareTo(ele2.getValue());
            }
        });
        Map<String,Double> sortedHashMap = new LinkedHashMap<>();
        for(Map.Entry<String,Double> entry: aList) {
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        for(Double balance: sortedHashMap.values()){

                Log.e("TAG balance"," "+balance );
            
        }
        RecyclerView recyclerView2=findViewById(R.id.rv_settledebts);
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getBaseContext());
        recyclerView2.setLayoutManager(linearLayoutManager2);
        SettleDebtsAdapter settleDebtsAdapter=new SettleDebtsAdapter(getBaseContext(),totalsum,clickedGrp,sortedHashMap);
        recyclerView2.setAdapter(settleDebtsAdapter);
    }
}
