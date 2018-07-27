package com.example.dell1.mainideaapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SplitEquallyAdapter extends RecyclerView.Adapter<SplitEquallyAdapter.ViewHolder>{

    Context context;
    private Double totalsum;
    private MyGroups clickedGrp;
    private int sizeCheckList=0;
    private GroupDao groupDao=MyAppApplication.getMyAppDatabase().getGroupDao();
    private GroupMemberDao groupMemberDao=MyAppApplication.getMyAppDatabase().getGroupMemberDao();

    public SplitEquallyAdapter(Context context,Double totalsum,MyGroups clickedGrp) {

        this.context=context;
        this.clickedGrp=clickedGrp;
        this.totalsum=totalsum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_row_rvsplitequally,parent,false);
        ArrayList<GroupMembers> checkList=new ArrayList<>();
        List<GroupMembers> groupMembersList=clickedGrp.getGroupMembersArrayList();
        for(GroupMembers g : groupMembersList){
            if(g.getGetPaidByOther()){
                checkList.add(g);
            }
        }
        sizeCheckList = checkList.size();
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){

             GroupMembers currentGrpMember=clickedGrp.getGroupMembersArrayList().get(position);
             String intial=currentGrpMember.getName().substring(0,1);
             holder.textView1.setText(intial);
             holder.textView2.setText(currentGrpMember.getName());
             if(!currentGrpMember.getGetPaidByOther()){
                 currentGrpMember.setAmountSplit(0.0);
                 holder.textView3.setText("0.0");
                 groupMemberDao.updateGroupMember(currentGrpMember);
                 groupDao.updateGroup(clickedGrp);
                 Log.e("TAG", "currentGrpMemberAmountSplitBEFORE: " + currentGrpMember.getAmountSplit());
             }
             else {
             currentGrpMember.setAmountSplit(splitEqual());
             holder.textView3.setText(String.valueOf(currentGrpMember.getAmountSplit()));
                 groupMemberDao.updateGroupMember(currentGrpMember);
                 groupDao.updateGroup(clickedGrp);
                 Log.e("TAG", "currentgrpmemberamountowedBEFORE: " + currentGrpMember.getAmountSplit());
             }
    }

    @Override
    public int getItemCount() {
        return clickedGrp.getGroupMembersArrayList().size();
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
    private double splitEqual(){

        Double equalSplitvalue=(totalsum/sizeCheckList);
        return BigDecimal.valueOf(equalSplitvalue).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
