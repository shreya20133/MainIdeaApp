package com.example.dell1.mainideaapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class SaveBill implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int groupDate;

    private String eventName;
    private Double totalAmountPaid;

    private List<GroupMembers> groupMembersWhoPaid;

    private List<GroupMembers> groupMembersSplitinto;

    private HashMap<GroupMembers,Double> mapSettleDebts;

    @Ignore
    public SaveBill() {
    }

    public SaveBill(String eventName, Double totalAmountPaid, List<GroupMembers> groupMembersWhoPaid, List<GroupMembers> groupMembersSplitinto, HashMap<GroupMembers,Double> mapSettleDebts) {
        this.eventName = eventName;
        this.totalAmountPaid = totalAmountPaid;
        this.groupMembersWhoPaid = groupMembersWhoPaid;
        this.groupMembersSplitinto = groupMembersSplitinto;
        this.mapSettleDebts = mapSettleDebts;
    }

    protected SaveBill(Parcel in) {
        eventName = in.readString();
        groupDate=in.readInt();
        if (in.readByte() == 0) {
            totalAmountPaid = null;
        } else {
            totalAmountPaid = in.readDouble();
        }
        groupMembersWhoPaid = in.createTypedArrayList(GroupMembers.CREATOR);
        groupMembersSplitinto = in.createTypedArrayList(GroupMembers.CREATOR);
    }

    public static final Creator<SaveBill> CREATOR = new Creator<SaveBill>() {
        @Override
        public SaveBill createFromParcel(Parcel in) {
            return new SaveBill(in);
        }

        @Override
        public SaveBill[] newArray(int size) {
            return new SaveBill[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(groupDate);
        parcel.writeString(eventName);
        if (totalAmountPaid == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(totalAmountPaid);
        }
        parcel.writeTypedList(groupMembersWhoPaid);
        parcel.writeTypedList(groupMembersSplitinto);
    }

    public int getGroupDate() {
        return groupDate;
    }

    public void setGroupDate(int groupDate) {
        this.groupDate = groupDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Double getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(Double totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    public List<GroupMembers> getGroupMembersWhoPaid() {
        return groupMembersWhoPaid;
    }

    public void setGroupMembersWhoPaid(List<GroupMembers> groupMembersWhoPaid) {
        this.groupMembersWhoPaid = groupMembersWhoPaid;
    }

    public List<GroupMembers> getGroupMembersSplitinto() {
        return groupMembersSplitinto;
    }

    public void setGroupMembersSplitinto(List<GroupMembers> groupMembersSplitinto) {
        this.groupMembersSplitinto = groupMembersSplitinto;
    }

    public HashMap<GroupMembers,Double> getMapSettleDebts() {
        return mapSettleDebts;
    }

    public void setMapSettleDebts(HashMap<GroupMembers,Double> mapSettleDebts) {
        this.mapSettleDebts = mapSettleDebts;
    }

    public static Creator<SaveBill> getCREATOR() {
        return CREATOR;
    }
}
