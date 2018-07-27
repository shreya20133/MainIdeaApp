package com.example.dell1.mainideaapp;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class EnterAmountAdapter extends RecyclerView.Adapter<EnterAmountAdapter.ViewHolder>{


    Context context;
    private MyGroups clickedGrp;
    private ArrayList<Double> doubleArrayList=new ArrayList<>();

    private GroupDao groupDao=MyAppApplication.getMyAppDatabase().getGroupDao();
    private GroupMemberDao groupMemberDao=MyAppApplication.getMyAppDatabase().getGroupMemberDao();

    public EnterAmountAdapter(Context context, MyGroups clickedGrp) {
        this.context=context;
        this.clickedGrp=clickedGrp;
    }

    @NonNull
    @Override
    public EnterAmountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_row_enter_amt_for_split,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnterAmountAdapter.ViewHolder holder, int position) {

        GroupMembers currentGrpMember=clickedGrp.getGroupMembersArrayList().get(position);
        String initial=currentGrpMember.getName().substring(0,1);
        holder.textView1.setText(initial);
        holder.textView2.setText(currentGrpMember.getName());
        if(!TextUtils.isEmpty(holder.editText.getText().toString())){
            doubleArrayList.add(Double.valueOf(holder.editText.getText().toString()));
        }
        else{
            doubleArrayList.add(0.0);
        }
//        groupMemberDao.updateGroupMember(currentGrpMember);
//        groupDao.updateGroup(clickedGrp);


    }

    @Override
    public int getItemCount() {
        return clickedGrp.getGroupMembersArrayList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView1;
        TextView textView2;
        EditText editText;

        public ViewHolder(View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.tv_itemrowenteramtforsplitIcon);
            textView2=itemView.findViewById(R.id.tv_itemrowenteramtforsplitName);
            editText=itemView.findViewById(R.id.etAddAmounttoSplit);
        }
    }

    public ArrayList<Double> getDoubleArrayList() {
        return doubleArrayList;
    }
}
