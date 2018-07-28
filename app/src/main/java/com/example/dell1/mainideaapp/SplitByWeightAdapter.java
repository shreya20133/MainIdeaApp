package com.example.dell1.mainideaapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SplitByWeightAdapter extends RecyclerView.Adapter<SplitByWeightAdapter.ViewHolder> {

    Context context;
    private MyGroups clickedGrp;
    Double totalsum;
    int totalWeight;

    public SplitByWeightAdapter(Context baseContext, MyGroups clickedGrp,Double totalsum,int totalWeight) {

        context=baseContext;
        this.totalsum=totalsum;
        this.clickedGrp=clickedGrp;
        this.totalWeight=totalWeight;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_row_rvsplitbyweight,parent,false);
        return new SplitByWeightAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupMembers currentGrpMember=clickedGrp.getGroupMembersArrayList().get(position);
        String intial=currentGrpMember.getName().substring(0,1);
        holder.textView1.setText(intial);
        holder.textView2.setText(currentGrpMember.getName());
        Log.e("TAG", "total weight: "+totalWeight);
        holder.textView3.setText(String.valueOf((currentGrpMember.getMemberweight()/totalWeight)*totalsum));
    }

    @Override
    public int getItemCount() {
        return clickedGrp.getGroupMembersArrayList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1,textView2,textView3;

        public ViewHolder(View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.userWticon);
            textView2=itemView.findViewById(R.id.tvsplitWtForwhomName);
            textView3=itemView.findViewById(R.id.tvSplitForWhomWtAmount);
        }
    }

//    private int getTotalWeight(){
//        int totalWeight=0;
//        for(GroupMembers g: clickedGrp.getGroupMembersArrayList()){
//            totalWeight+=g.getMemberweight();
//        }
//        return totalWeight;
//    }

}
