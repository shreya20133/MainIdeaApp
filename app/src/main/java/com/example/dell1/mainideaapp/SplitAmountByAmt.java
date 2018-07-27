package com.example.dell1.mainideaapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SplitAmountByAmt extends AppCompatActivity{

    Double totalsum;
    MyGroups clickedGrp;
    GroupDao groupDao;
    GroupMemberDao groupMemberDao;
    Double checkTotalAmount=0.0;
    ArrayList<Double> doubleArrayList=new ArrayList<>();
    TextView textViewtotalAmount;
    RecyclerView recyclerViewEnterAmount;
    Button buttonDone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick_split_by_amount);

        textViewtotalAmount=findViewById(R.id.splitByAmt_TotalAmount);
        recyclerViewEnterAmount=findViewById(R.id.rv_enterAmountForSplit);
        buttonDone=findViewById(R.id.doneEnterAmount);

        final Intent intent=getIntent();
        totalsum=intent.getDoubleExtra("total sum",0.0);
        final String groupDate=intent.getStringExtra("grpDate");

        groupDao=MyAppApplication.getMyAppDatabase().getGroupDao();
        groupMemberDao=MyAppApplication.getMyAppDatabase().getGroupMemberDao();
        clickedGrp=groupDao.getgroupWithId(groupDate);

        textViewtotalAmount.setText(String.valueOf(totalsum));
        LinearLayoutManager llm=new LinearLayoutManager(getBaseContext());
        recyclerViewEnterAmount.setLayoutManager(llm);
        EnterAmountAdapter enterAmountAdapter=new EnterAmountAdapter(getBaseContext(),clickedGrp);
        recyclerViewEnterAmount.setAdapter(enterAmountAdapter);


        doubleArrayList = enterAmountAdapter.getDoubleArrayList();
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GroupMembers curentGrpMember;

                for (int i = 0; i < doubleArrayList.size(); i++) {
                    curentGrpMember = clickedGrp.getGroupMembersArrayList().get(i);
                    curentGrpMember.setAmountSplit(doubleArrayList.get(i));
                    groupMemberDao.updateGroupMember(curentGrpMember);
                    groupDao.updateGroup(clickedGrp);
                }
                Log.e("tag before done",clickedGrp.getGroupMembersArrayList().get(1).getAmountSplit().toString());
                for (GroupMembers g : clickedGrp.getGroupMembersArrayList()) {
                    checkTotalAmount += g.getAmountSplit();
                }
                if (checkTotalAmount.equals(totalsum)) {
                    Intent intent1 = new Intent(getBaseContext(), ShowSplitByAmt.class);
                    intent1.putExtra("total amount", totalsum);
                    startActivity(intent1);
                }
                else{
                    Toast.makeText(getBaseContext(),"Please enter amount",Toast.LENGTH_SHORT).show();
                }
// else {
//                    showDialog();
//                }
                groupDao.updateGroup(clickedGrp);
//                else{
//                    Toast.makeText(getBaseContext(),"please win",Toast.LENGTH_SHORT).show();
//                }
//                if(!clickedGrp.getGroupMembersArrayList().isEmpty()) {
//                    for (GroupMembers g : clickedGrp.getGroupMembersArrayList()) {
//                        checkTotalAmount += g.getAmountSplit();
//                    }
//                    if (!checkTotalAmount.equals(totalsum)) {
//                        showDialog();
//                    } else {
//                          Intent intent1 = new Intent(getBaseContext(), ShowSplitByAmt.class);
//                        intent1.putExtra("new total amount",checkTotalAmount);
//                        startActivity(intent1);
//                    }
//                }
            }
        });




//        TextView textView=findViewById(R.id.tvdisplayTotalAmount);
//        RecyclerView recyclerView1=findViewById(R.id.rv_splitEqually);
//        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getBaseContext());
//
//        textView.setText(String.valueOf(totalsum));
//        recyclerView1.setLayoutManager(linearLayoutManager1);
//        SplitByAmountAdapter splitByAmountAdapter =new SplitByAmountAdapter(getBaseContext(),totalsum,clickedGrp);
//        recyclerView1.setAdapter(splitByAmountAdapter);
//
////        RecyclerView recyclerView2=findViewById(R.id.rv_settledebts);
////        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getBaseContext());
////        recyclerView2.setLayoutManager(linearLayoutManager2);
////        SettleDebtsAdapter settleDebtsAdapter=new SettleDebtsAdapter(getBaseContext(),groupMembersNewArrayList,totalsum);
////        recyclerView2.setAdapter(settleDebtsAdapter);
//    }
    }
    private void showDialog(){
         AlertDialog alertDialog = new AlertDialog.Builder(getBaseContext())
                .setTitle("TotalAmount Changed!")
                .setMessage("You changed the total amount Paid." +
                        "Do you want to update the Total Amount?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getBaseContext(),"hi from yes",Toast.LENGTH_SHORT).show();
//                        Intent intent1 = new Intent(getBaseContext(), ShowSplitByAmt.class);
//                        intent1.putExtra("new total amount",checkTotalAmount);
//                        startActivity(intent1);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .create();
        alertDialog.show();
    }
}
