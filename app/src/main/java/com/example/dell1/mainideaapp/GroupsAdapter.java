package com.example.dell1.mainideaapp;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder>{

    Context context;
    List<MyGroups> myGroupsArrayList;
    SaveBillDao saveBillDao=MyAppApplication.getMyAppDatabase().getSaveBillDao();

    public GroupsAdapter(Context context,List<MyGroups> myGroupsArrayList) {
        this.context=context;
        this.myGroupsArrayList=myGroupsArrayList;
    }

    @NonNull
    @Override
    public GroupsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView= LayoutInflater.from(context).inflate(R.layout.item_row_group,parent,false);
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupsAdapter.ViewHolder holder, int position) {

        final MyGroups currentGroup=myGroupsArrayList.get(position);
        holder.grpname.setText(currentGroup.getGroupName());
        String intial=currentGroup.getGroupName().substring(0,1);
        holder.grpicon.setText(intial);
        holder.grpCreateDate.setText(currentGroup.getTimeofcreation());
        holder.Group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,AddGroupContent.class);
//                intent.putExtra("GrpName",currentGroup.getGroupName());
                intent.putExtra("GrpDate",currentGroup.getTimeofcreation());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myGroupsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView grpicon;
        TextView grpname;
        TextView grpBalance;
        TextView grpCreateDate;
        CardView Group;

        public ViewHolder(View itemView) {
            super(itemView);
            grpicon=itemView.findViewById(R.id.Group_icon);
            grpname=itemView.findViewById(R.id.Group_name);
            grpBalance=itemView.findViewById(R.id.Group_Balance);
            grpCreateDate=itemView.findViewById(R.id.Group_createDate);
            Group=itemView.findViewById(R.id.itemGroup);

        }
    }
}
