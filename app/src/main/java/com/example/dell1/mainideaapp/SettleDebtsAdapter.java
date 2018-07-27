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
import java.util.HashMap;

public class SettleDebtsAdapter extends RecyclerView.Adapter<SettleDebtsAdapter.ViewHolder> {

    Context baseContext;
    ArrayList<GroupMembers> groupMembersNewArrayList;
    Double totalsum;
    ArrayList<GroupMembers> payerList,owerList;

    public SettleDebtsAdapter(Context baseContext, ArrayList<GroupMembers> groupMembersNewArrayList, Double totalsum) {

        this.baseContext=baseContext;
        this.groupMembersNewArrayList=groupMembersNewArrayList;
        this.totalsum=totalsum;
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

        GroupMembers currentGrpMember=groupMembersNewArrayList.get(position);
        Double Payerbalance,Owerbalance,SettleBalance;
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
        }
    }
}
