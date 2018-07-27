package com.example.dell1.mainideaapp;

import android.os.Parcel;
import android.os.Parcelable;

class Overview implements Parcelable {

    String grpName;
    Double amountBalance;  //positive balance matlab lene hai paise auro se
                          //negative balance matlab dene hai paise auro ko

    public Overview(String grpName, Double amountBalance) {
        this.grpName = grpName;
        this.amountBalance = amountBalance;
    }

    protected Overview(Parcel in) {
        grpName = in.readString();
        if (in.readByte() == 0) {
            amountBalance = null;
        } else {
            amountBalance = in.readDouble();
        }
    }

    public static final Creator<Overview> CREATOR = new Creator<Overview>() {
        @Override
        public Overview createFromParcel(Parcel in) {
            return new Overview(in);
        }

        @Override
        public Overview[] newArray(int size) {
            return new Overview[size];
        }
    };

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }

    public Double getAmountBalance() {
        return amountBalance;
    }

    public void setAmountBalance(Double amountBalance) {
        this.amountBalance = amountBalance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(grpName);
        if (amountBalance == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(amountBalance);
        }
    }
}
