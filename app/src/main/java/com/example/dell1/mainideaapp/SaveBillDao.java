package com.example.dell1.mainideaapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.HashMap;
import java.util.List;

@Dao
public interface SaveBillDao{

   @Insert
   void insertSaveBill1(SaveBill... saveBills);


    @Update
    void updateSavedBill(SaveBill... saveBill);

    @Delete
    void deleteSaveBill(SaveBill... saveBill);


    @Query("SELECT * FROM SaveBill")
    List<SaveBill> getAllBills();

    @Query("SELECT * FROM SaveBill WHERE groupDate = :id")
    SaveBill getSaveBillWithId(String id);


}
