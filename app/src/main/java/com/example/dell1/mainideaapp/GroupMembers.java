package com.example.dell1.mainideaapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

@Entity
public class GroupMembers implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private Boolean didPay;

    private Boolean getPaidByOther;

    private Double paidAmount;

    private Double AmountSplit;

    private Double AmountMemberOwestoOthers;

    @Ignore
    public GroupMembers() {
    }

    public GroupMembers(String name, Boolean didPay,Boolean getPaidByOther, Double paidAmount,
                        Double AmountMemberOwestoOthers, Double AmountSplit) {
        this.name = name;
        this.didPay = didPay;
        this.getPaidByOther=getPaidByOther;
        this.paidAmount = paidAmount;
        this.AmountMemberOwestoOthers=AmountMemberOwestoOthers;
        this.AmountSplit=AmountSplit;
    }

    protected GroupMembers(Parcel in) {
        id=in.readInt();
        name = in.readString();
        byte tmpDidPay = in.readByte();
        didPay = tmpDidPay == 0 ? null : tmpDidPay == 1;

        byte tmpGetPaidByOther=in.readByte();
        getPaidByOther=tmpGetPaidByOther==0 ? null : tmpGetPaidByOther ==1;

        if (in.readByte() == 0) {
            paidAmount = null;
        } else {
            paidAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            AmountMemberOwestoOthers = null;
        } else {
            AmountMemberOwestoOthers = in.readDouble();
        }
        if (in.readByte() == 0) {
            AmountSplit = null;
        } else {
            AmountSplit = in.readDouble();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeByte((byte) (didPay == null ? 0 : didPay ? 1 : 2));
        dest.writeByte((byte) (getPaidByOther == null ? 0 : getPaidByOther ? 1 : 2));

        if (paidAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(paidAmount);
        }
        if (AmountMemberOwestoOthers == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(AmountMemberOwestoOthers);
        }
        if (AmountSplit == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(AmountSplit);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GroupMembers> CREATOR = new Creator<GroupMembers>() {
        @Override
        public GroupMembers createFromParcel(Parcel in) {
            return new GroupMembers(in);
        }

        @Override
        public GroupMembers[] newArray(int size) {
            return new GroupMembers[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Creator<GroupMembers> getCREATOR() {
        return CREATOR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGetPaidByOther() {
        return getPaidByOther;
    }

    public void setGetPaidByOther(Boolean getPaidByOther) {
        this.getPaidByOther = getPaidByOther;
    }

    public Boolean getDidPay() {
        return didPay;
    }

    public void setDidPay(Boolean didPay) {
        this.didPay = didPay;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Double getAmountMemberOwestoOthers() {
        return AmountMemberOwestoOthers;
    }

    public void setAmountMemberOwestoOthers(Double amountMemberOwestoOther) {
        AmountMemberOwestoOthers = amountMemberOwestoOther;
    }

    public Double getAmountSplit() {
        return AmountSplit;
    }

    public void setAmountSplit(Double amountSplit) {
        AmountSplit = amountSplit;
    }

}
