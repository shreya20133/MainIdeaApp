package com.example.dell1.mainideaapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@TypeConverters({Converters.class,HashMapConverter.class})
@Database(entities = {MyGroups.class,GroupMembers.class,SaveBill.class},version = 8)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract GroupDao getGroupDao();
    public abstract GroupMemberDao getGroupMemberDao();
    public abstract SaveBillDao getSaveBillDao();
}                                                                                   
