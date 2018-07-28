package com.example.dell1.mainideaapp;

import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.StrictMath.abs;

public class FinalResult extends AppCompatActivity{

    SaveBillDao saveBillDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalresultlayout);
        Intent i=getIntent();
        String grpdate=i.getStringExtra("clickedGrpDate");
        Double totalsum=i.getDoubleExtra("totalsum",0.0);
        MyGroups clickedGrp=MyAppApplication.getMyAppDatabase().getGroupDao().getgroupWithId(grpdate);
        MyAppApplication.getMyAppDatabase().getGroupDao().updateGroup(clickedGrp);
//        SaveBill saveBill=populate(clickedGrp,totalsum);
//        MyAppApplication.getMyAppDatabase().getSaveBillDao().updateSavedBill(saveBill);
        TextView textView1=findViewById(R.id.finaltotalPaid);
        textView1.setText(String.valueOf(totalsum));
        TextView textView2=findViewById(R.id.finaleventName);
        textView2.setText(populate(clickedGrp,totalsum).getEventName());
        Log.e("savebill",populate(clickedGrp,totalsum).getEventName()) ;
        RecyclerView recyclerView1=findViewById(R.id.rv_finalAmountPaidBy);
        LinearLayoutManager llm=new LinearLayoutManager(getBaseContext());
        recyclerView1.setLayoutManager(llm);
//        GroupMembersAdapter groupMembersAdapter=new GroupMembersAdapter(this,saveBill.getGroupMembersWhoPaid());
//        recyclerView1.setAdapter(groupMembersAdapter);
        RecyclerView recyclerView2=findViewById(R.id.rv_finalAmountSplitInto);
        LinearLayoutManager llm2=new LinearLayoutManager(getBaseContext());
        recyclerView2.setLayoutManager(llm2);
        SplitEquallyAdapter splitEquallyAdapter=new SplitEquallyAdapter(this,totalsum,clickedGrp);
        recyclerView2.setAdapter(splitEquallyAdapter);
        RecyclerView recyclerView3=findViewById(R.id.rv_finalSettleDebts);
        LinearLayoutManager llm3=new LinearLayoutManager(getBaseContext());
        recyclerView2.setLayoutManager(llm3);
//        SettleDebtsAdapter settleDebtsAdapter=new SettleDebtsAdapter(this,saveBill);
//        recyclerView2.setAdapter(settleDebtsAdapter);


    }
    private SaveBill populate(MyGroups clickedGrp,Double totalsum){
            SaveBill saveBill1=new SaveBill();
            String grpEvent=clickedGrp.getEventName();
            saveBill1.setEventName(grpEvent);
            Double TotalAmountPaid=totalsum;
            saveBill1.setTotalAmountPaid(TotalAmountPaid);
            ArrayList<GroupMembers> groupMembersWhoPaidList=new ArrayList<>();
            for(GroupMembers g: clickedGrp.getGroupMembersArrayList()){
                if(g.getPaidAmount()>0){
                    groupMembersWhoPaidList.add(g);
                }
            }
            ArrayList<GroupMembers> groupMembersSplitIntoList;
            groupMembersSplitIntoList=(ArrayList<GroupMembers>) clickedGrp.getGroupMembersArrayList();
            saveBill1.setGroupMembersWhoPaid(groupMembersWhoPaidList);
            for(GroupMembers g: clickedGrp.getGroupMembersArrayList()){
                g.setBalance(g.getPaidAmount()-g.getAmountSplit());
            }
            HashMap<GroupMembers,Double> hashMap=new HashMap<>();
            for(GroupMembers g: clickedGrp.getGroupMembersArrayList()){
                hashMap.put(g,g.getBalance());
            }
            Set<Map.Entry<GroupMembers, Double>> set = hashMap.entrySet();
            List<Map.Entry<GroupMembers, Double>> list = new ArrayList<>(set);
            Collections.sort( list, new Comparator<Map.Entry<GroupMembers,Double>>()
            {
                public int compare( Map.Entry<GroupMembers, Double> o1, Map.Entry<GroupMembers,Double> o2 )
                {
                    return (o1.getValue()).compareTo( o2.getValue() );
                }
            } );
            for(Map.Entry<GroupMembers, Double> entry:list){
                Log.e("MAP","KEY : "+entry.getKey().getName()+" ==== "+"vALUE: "+entry.getValue());
            }
            HashMap<GroupMembers,Double> payerHashMap=new HashMap<>();
        HashMap<GroupMembers,Double> BorrowerHashMap=new HashMap<>();
        HashMap<GroupMembers,Double> FreeFromDebtsHashMap=new HashMap<>();

            for(Map.Entry<GroupMembers,Double> entry:list){
                if(entry.getValue()>0){
                    payerHashMap.put(entry.getKey(),entry.getValue());
                }
                else if(entry.getValue()<0){
                    BorrowerHashMap.put(entry.getKey(),entry.getValue());
                }
                else{
                    FreeFromDebtsHashMap.put(entry.getKey(),entry.getValue());
                }
            }
        Set<Map.Entry<GroupMembers, Double>> payerset = hashMap.entrySet();
        List<Map.Entry<GroupMembers, Double>> payerlist = new ArrayList<>(payerset);
        Set<Map.Entry<GroupMembers, Double>> borrowerset = hashMap.entrySet();
        List<Map.Entry<GroupMembers, Double>> borrowerlist = new ArrayList<>(borrowerset);
        Set<Map.Entry<GroupMembers, Double>> freeset = hashMap.entrySet();
        List<Map.Entry<GroupMembers, Double>> freelist = new ArrayList<>(freeset);
           for(Map.Entry<GroupMembers,Double> entryp:payerlist){
               for(Map.Entry<GroupMembers,Double> entryb:borrowerlist){
                   if(entryp.getValue()>=abs(entryb.getValue())){
                       entryp.setValue(entryp.getValue()-abs(entryb.getValue()));
                       entryb.setValue(entryb.getValue()+abs(entryb.getValue()));
                   }

               }
           }
//        HashMap<GroupMembers,Double> hashMap=new HashMap<>();
//        for(GroupMembers g: clickedGrp.getGroupMembersArrayList()){
//            hashMap.put(g,g.getBalance());
//        }
            saveBill1=new SaveBill(grpEvent,TotalAmountPaid,groupMembersWhoPaidList,groupMembersSplitIntoList,hashMap);
            MyAppApplication.getMyAppDatabase().getSaveBillDao().insertSaveBill1(saveBill1);
            MyAppApplication.getMyAppDatabase().getSaveBillDao().updateSavedBill(saveBill1);
            return saveBill1;
      }
}
