package com.example.dell1.mainideaapp;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.PaintDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MyViewHolder>{

    Context ctx;
    ArrayList<MembersAdded> membersArrayList;

    public MembersAdapter(Context ctx, ArrayList<MembersAdded> membersArrayList) {
        this.ctx=ctx;
        this.membersArrayList=membersArrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView memberName;
        TextView memberImage;
        public MyViewHolder(View itemView) {
            super(itemView);
            memberName=itemView.findViewById(R.id.member_name);
            memberImage=itemView.findViewById(R.id.member_image);
        }
    }

    @NonNull
    @Override
    public MembersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(ctx).inflate(R.layout.item_row_member,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MembersAdapter.MyViewHolder holder, int position) {

        MembersAdded currentMember=membersArrayList.get(position);
        holder.memberName.setText(currentMember.getMemName());
        holder.memberImage.setText(currentMember.getMemImage());

    }

    @Override
    public int getItemCount() {
        return membersArrayList.size();
    }


}
