package com.example.dell1.mainideaapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class FragmentMyGroups extends Fragment {

    EditText editTextGroupName;
    String GroupName;
    RecyclerView rvMyGroups;
    FloatingActionButton fab;
    List<MyGroups> myGroupsList;
    GroupsAdapter groupsAdapter;
    List<GroupMembers> grpMembersArrayList;

    public FragmentMyGroups() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_mygroups,container,false);
        rvMyGroups=view.findViewById(R.id.rv_myGroups);
        LinearLayoutManager llm=new LinearLayoutManager(getContext());
        rvMyGroups.setLayoutManager(llm);

        myGroupsList = MyAppApplication.getMyAppDatabase().getGroupDao().getAllGroups();
        groupsAdapter=new GroupsAdapter(getContext(),myGroupsList);
        rvMyGroups.setAdapter(groupsAdapter);

        fab=view.findViewById(R.id.fabAddGroup);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCreateGroupDialog(view);

            }
        });
        
        return view;
    }

    private void showCreateGroupDialog(View view) {

        View dialogView=LayoutInflater.from(view.getContext()).inflate(R.layout.dialogcreategroup,null,true);
        editTextGroupName=dialogView.findViewById(R.id.etGroupName);
        AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
        builder.setView(dialogView)
                .setPositiveButton("CREATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        GroupName = editTextGroupName.getText().toString();
                        if (!TextUtils.isEmpty(GroupName)) {

                            MyAppApplication.getMyAppDatabase().getGroupDao().insertGroup(new MyGroups(GroupName,
                                    Calendar.getInstance().getTime().toString()
                                    ,grpMembersArrayList,""));


                            //groupsAdapter do baar set kia hai notifyDatasetChanged() kaam ni karra;
                            myGroupsList=MyAppApplication.getMyAppDatabase().getGroupDao().getAllGroups();
                            groupsAdapter = new GroupsAdapter(getContext(),myGroupsList);
                            rvMyGroups.setAdapter(groupsAdapter);
                        }
                        else{
                            Toast.makeText(getContext(),"Group Name can't be empty",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .create().show();
    }
}
