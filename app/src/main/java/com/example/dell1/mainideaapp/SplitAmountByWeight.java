package com.example.dell1.mainideaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SplitAmountByWeight extends AppCompatActivity{

    Double totalsum;
    GroupDao groupDao;
    GroupMemberDao groupMemberDao;
    public ArrayList<EditModel> editModelArrayList;
    TextView textViewtotalAmount;
    RecyclerView recyclerViewEnterWeight;
    Button buttonDone;
    int editModelArrayListSize=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick_split_by_weight);

        textViewtotalAmount = findViewById(R.id.splitByWeight_TotalAmount);
        recyclerViewEnterWeight = findViewById(R.id.rv_enterWeightForSplit);
        buttonDone = findViewById(R.id.doneEnterWeight);

        final Intent intent = getIntent();
        totalsum = intent.getDoubleExtra("total sum", 0.0);
        final String groupDate = intent.getStringExtra("grpDate");

        groupDao = MyAppApplication.getMyAppDatabase().getGroupDao();
        groupMemberDao = MyAppApplication.getMyAppDatabase().getGroupMemberDao();
        final MyGroups clickedGrp = groupDao.getgroupWithId(groupDate);
        editModelArrayListSize=clickedGrp.getGroupMembersArrayList().size();
        editModelArrayList = populateList();
        textViewtotalAmount.setText(String.valueOf(totalsum));
        EnterWeightAdapter enterWeightAdapter = new EnterWeightAdapter(getBaseContext(), clickedGrp, editModelArrayList);
        recyclerViewEnterWeight.setAdapter(enterWeightAdapter);
        recyclerViewEnterWeight.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkTotalWt=0;
                for (int i = 0; i < editModelArrayList.size(); i++) {

                    clickedGrp.getGroupMembersArrayList().get(i).setMemberweight(Integer.valueOf(editModelArrayList.get(i).getEditTextValue()));
                    checkTotalWt+=Integer.valueOf(editModelArrayList.get(i).getEditTextValue());
                }
                Log.e("checkTotalWt",""+checkTotalWt+" grpememweight"+clickedGrp.getGroupMembersArrayList().get(1).getMemberweight());
                groupDao.updateGroup(clickedGrp);
                if(checkTotalWt!=0)
                {Intent intent1 = new Intent(getBaseContext(), ShowSplitByWeight.class);
                intent1.putExtra("totalWeight",checkTotalWt);
                intent1.putExtra("total amount", totalsum);
                intent1.putExtra("grpDate",groupDate);
                startActivity(intent1);}
                else{
                    Toast.makeText(getBaseContext(),"All Members can't be assigned weight of zero!",Toast.LENGTH_SHORT).show();
                }
// else {
//                    showDialog();
//                }

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
////                }
//            }
            }
//
        });

    }
    private ArrayList<EditModel> populateList() {
        ArrayList<EditModel> list=new ArrayList<>();
        for (int i=0;i<editModelArrayListSize;i++){
            EditModel editModel=new EditModel();
            list.add(editModel);
        }
        return list;
    }

//    private void showDialog(){
//         AlertDialog alertDialog = new AlertDialog.Builder(getBaseContext())
//                .setTitle("TotalAmount Changed!")
//                .setMessage("You changed the total amount Paid." +
//                        "Do you want to update the Total Amount?")
//                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(getBaseContext(),"hi from yes",Toast.LENGTH_SHORT).show();
////                        Intent intent1 = new Intent(getBaseContext(), ShowSplitByAmt.class);
////                        intent1.putExtra("new total amount",checkTotalAmount);
////                        startActivity(intent1);
//                    }
//                })
//                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//                    }
//                })
//                .create();
//        alertDialog.show();
//    }
}
