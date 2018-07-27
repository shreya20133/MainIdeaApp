package com.example.dell1.mainideaapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface GroupMemberDao {

    @Insert
    void insertGroupMember(GroupMembers... groupMembers);

    @Delete
    void deleteGroupMember(GroupMembers groupMembers);

    @Insert
    void insertGroupMembersList(List<GroupMembers> groupMembersArrayList);

    @Update
    void updateGroupMember(GroupMembers groupMembers);


    @Query("SELECT * FROM GroupMembers")
    List<GroupMembers> getAllGroupMembers();

    @Query("SELECT * FROM GroupMembers WHERE id = :id")
    GroupMembers groupMemberWithId(int id);

}
