package com.example.dell1.mainideaapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SplitByAmountAdapter extends RecyclerView.Adapter<SplitByAmountAdapter.ViewHolder> {

    Context context;
    private MyGroups clickedGrp;

    public SplitByAmountAdapter(Context baseContext,MyGroups clickedGrp) {

        context=baseContext;
        this.clickedGrp=clickedGrp;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_row_rvsplitbyamt,parent,false);
        return new SplitByAmountAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupMembers currentGrpMember=clickedGrp.getGroupMembersArrayList().get(position);
        String intial=currentGrpMember.getName().substring(0,1);
        holder.textView1.setText(intial);
        holder.textView2.setText(currentGrpMember.getName());
        holder.textView3.setText(String.valueOf(currentGrpMember.getAmountSplit()));
    }

    @Override
    public int getItemCount() {
        return clickedGrp.getGroupMembersArrayList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1,textView2,textView3;

        public ViewHolder(View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.userAmticon);
            textView2=itemView.findViewById(R.id.tvsplitAmtForwhomName);
            textView3=itemView.findViewById(R.id.tvSplitForWhomAmtAmount);
        }
    }
}
