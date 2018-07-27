package com.example.dell1.mainideaapp;

public class PaidForMemberAndAmount {

    String memname;
    Double amountPaidFormemName;

    public PaidForMemberAndAmount(String memname, Double amountPaidFormemName) {
        this.memname = memname;
        this.amountPaidFormemName = amountPaidFormemName;
    }

    public String getMemname() {
        return memname;
    }

    public void setMemname(String memname) {
        this.memname = memname;
    }

    public Double getAmountPaidFormemName() {
        return amountPaidFormemName;
    }

    public void setAmountPaidFormemName(Double amountPaidFormemName) {
        this.amountPaidFormemName = amountPaidFormemName;
    }
}
