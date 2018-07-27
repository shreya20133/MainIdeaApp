package com.example.dell1.mainideaapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ForWhomAdapter extends RecyclerView.Adapter<ForWhomAdapter.ViewHolder> {

    private Context baseContext;
    private GroupDao groupDao=MyAppApplication.getMyAppDatabase().getGroupDao();
    private GroupMemberDao groupMemberDao=MyAppApplication.getMyAppDatabase().getGroupMemberDao();
    private MyGroups clickedGrp;

    public ForWhomAdapter(Context baseContext,MyGroups clickedGrp) {

        this.baseContext=baseContext;
        this.clickedGrp=clickedGrp;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView= LayoutInflater.from(baseContext).inflate(R.layout.item_row_forwhom,parent,false);
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final GroupMembers currentgrpMember=clickedGrp.getGroupMembersArrayList().get(position);
        currentgrpMember.setId(position);
        String initial=currentgrpMember.getName().substring(0,1);
        holder.memberForWhomPaidIcon.setText(initial);
        holder.memberForWhomPaidName.setText(currentgrpMember.getName());
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(currentgrpMember.getGetPaidByOther());       //getPaidByother is initially set true for all
        Log.e("BEFORECHECKCHANGED",""+currentgrpMember.getGetPaidByOther());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentgrpMember.setGetPaidByOther(isChecked);
                groupMemberDao.updateGroupMember(currentgrpMember);
                groupDao.updateGroup(clickedGrp);
                Log.e("TAG GROUP DAO","GETPAIDBYOTHER"+currentgrpMember.getGetPaidByOther());
//                Boolean test=currentgrpMember.getGetPaidByOther();
//                Log.e("TAG", "onCheckedChanged: " + test);
//                if(currentgrpMember.getGetPaidByOther()) {
//                    checkedArrayList.add(currentgrpMember);
//                    Log.e("TAG", "inCheckedArrayList: " + currentgrpMember.getName());
//                    notifyDataSetChanged();
//                }
//                else{
//                    currentgrpMember.setGetPaidByOther();
//                    checkedArrayList.remove(currentgrpMember);
//                    notifyDataSetChanged();
//                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return clickedGrp.getGroupMembersArrayList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView memberForWhomPaidName;
        TextView memberForWhomPaidIcon;
        CheckBox checkBox;
        public ViewHolder(View itemView) {
            super(itemView);
            memberForWhomPaidName=itemView.findViewById(R.id.member_forWhomPaidName);
            memberForWhomPaidIcon=itemView.findViewById(R.id.member_forWhomPaidImage);
            checkBox=itemView.findViewById(R.id.checkBoxForPaidForMember);
        }
    }
}
