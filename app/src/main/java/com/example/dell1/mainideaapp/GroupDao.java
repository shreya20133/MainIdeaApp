package com.example.dell1.mainideaapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface GroupDao {

    @Insert
    void insertGroup(MyGroups... groups);

    @Delete
    void deleteGroup(MyGroups groups);

    @Insert
    void insertGroupsList(List<MyGroups> groupsArrayList);

    @Update
    void updateGroup(MyGroups groups);


    @Query("SELECT * FROM MyGroups")
    List<MyGroups> getAllGroups();

    @Query("SELECT * FROM MyGroups WHERE timeofcreation = :timeid")
    MyGroups getgroupWithId(String timeid);

    
}
