package com.example.dell1.mainideaapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SettleDebtsAdapter extends RecyclerView.Adapter<SettleDebtsAdapter.ViewHolder> {

    private  MyGroups clickedGrp;
    private Context baseContext;
    private Double totalsum;
    private ArrayList<Double> balanceList;
    private HashMap<String,Double> hashMap;

    public SettleDebtsAdapter(Context baseContext,Double totalsum,MyGroups clickedGrp) {

        this.baseContext=baseContext;
        this.totalsum=totalsum;
        this.clickedGrp=clickedGrp;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view= LayoutInflater.from(baseContext).inflate(R.layout.item_row_rvsettledebts,parent,false) ;
//        payerList = new ArrayList<>();
//        owerList=new ArrayList<>();
//        for(int i=0;i<groupMembersNewArrayList.size();i++){
//            if(groupMembersNewArrayList.get(i).getDidPay()){
//                payerList.add(groupMembersNewArrayList.get(i));
//                Log.e("tagpayer",""+payerList.get(i).getName());
//            }
//            else{
//                   owerList.add(groupMembersNewArrayList.get(i));
//            }
//        }
     return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettleDebtsAdapter.ViewHolder holder, int position) {

        GroupMembers currentGrpMember=clickedGrp.getGroupMembersArrayList().get(position);
        Double balance=currentGrpMember.getPaidAmount()-currentGrpMember.getAmountSplit();
        balanceList.add(balance);
        Collections.sort(balanceList);
        hashMap.put(currentGrpMember.getName(),balance);
        
//        Double balance=currentGrpMember.getPaidAmount()-currentGrpMember.getAmountMemberOwestoOthers();
//        if(balance>0){
//            currentGrpMember.setAmountOthersOwetoMember(balance);
//        }
//        else if(balance<0){
//            currentGrpMember.setAmountMemberOwestoOthers(balance);
//        }
//        else{
//            currentGrpMember.setAmountMemberOwestoOthers(0.0);
//        }
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewOwerIcon,textViewOwerName,textViewPayerIcon,textViewPayerName,textViewAmtOwerToPayer;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewOwerIcon=itemView.findViewById(R.id.tv_rvsettleOwericon);
            textViewOwerName=itemView.findViewById(R.id.tv_rvsettleOwerName);
            textViewPayerIcon=itemView.findViewById(R.id.tv_rvsettlePayericon);
            textViewPayerName=itemView.findViewById(R.id.tv_rvsettlePayerName);
            textViewAmtOwerToPayer=itemView.findViewById(R.id.tv_rvsettleamountowertoPayer);
            balanceList=new ArrayList<>();
            hashMap=new HashMap<>();
        }
    }
}
