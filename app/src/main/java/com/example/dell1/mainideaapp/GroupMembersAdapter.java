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

public class GroupMembersAdapter extends RecyclerView.Adapter<GroupMembersAdapter.ViewHolder>{

    private Context ctx;
    private List<GroupMembers> groupMembersArrayList;

    public GroupMembersAdapter(Context baseContext, List<GroupMembers> groupMembersArrayList) {
        ctx=baseContext;
        this.groupMembersArrayList=groupMembersArrayList;
    }

    @NonNull
    @Override
    public GroupMembersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView= LayoutInflater.from(ctx).inflate(R.layout.item_row_member,parent,false);
        return  new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupMembersAdapter.ViewHolder holder, int position) {

        GroupMembers currentgrpMember=groupMembersArrayList.get(position);
        String initial=currentgrpMember.getName().substring(0,1);
        holder.memberIcon.setText(initial);
        holder.memberName.setText(currentgrpMember.getName());
        holder.memberAmountPaid.setText(String.valueOf(currentgrpMember.getPaidAmount()));
    }

    @Override
    public int getItemCount() {
        return groupMembersArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView memberName;
        TextView memberIcon;
        TextView memberAmountPaid;
        public ViewHolder(View itemView) {
            super(itemView);
            memberName=itemView.findViewById(R.id.member_name);
            memberIcon=itemView.findViewById(R.id.member_image);
            memberAmountPaid=itemView.findViewById(R.id.member_paidAmount);
        }
    }
}
