package com.example.dell1.mainideaapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class SplitByAmountAdapter extends RecyclerView.Adapter<SplitByAmountAdapter.ViewHolder> {

    Context context;
    private MyGroups clickedGrp;
    private Double totalsum;
    private int sizeCheckList=0;

    public SplitByAmountAdapter(Context baseContext,Double totalsum,MyGroups clickedGrp) {

        context=baseContext;
        this.clickedGrp=clickedGrp;
        this.totalsum=totalsum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_row_rvsplitbyamt,parent,false);
         ArrayList<GroupMembers> checkList=new ArrayList<>();
//        for(int i=0;i<groupMembersNewArrayList.size();i++){
//            if(groupMembersNewArrayList.get(i).getGetPaidByOther()){
//                checkList.add(groupMembersNewArrayList.get(i));
//            }
//        }
        sizeCheckList = checkList.size();
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//
//        String intial=currentGrpMember.getName().substring(0,1);
//        holder.textView1.setText(intial);
//        holder.textView2.setText(currentGrpMember.getName());
//        if(!currentGrpMember.getGetPaidByOther()){
//            currentGrpMember.setAmountSplit(0.0);
//            holder.textView3.setText("0.0");
//        }
//        else {
//            //setamountsplit
//            holder.textView3.setText(String.valueOf(currentGrpMember.getAmountSplit()));
//        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1,textView2,textView3;

        public ViewHolder(View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.usericon);
            textView2=itemView.findViewById(R.id.tvsplitForwhomName);
            textView3=itemView.findViewById(R.id.tvSplitForWhomAmount);
        }
    }
}
