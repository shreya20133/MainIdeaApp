package com.example.dell1.mainideaapp;


public class MyModel {

    public static final int EVENT_TYPE=0;
    public static final int WHOPAID_TYPE=1;
    public static final int FORWHOMPAID_TYPE=2;
    public static final int SPLIT_TYPE=3;

    public int type;
    public int data;
    public String text;

    public MyModel(int type)
    {
        this.type=type;
    }
}
