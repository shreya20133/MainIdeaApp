package com.example.dell1.mainideaapp;

class MembersAdded {

    String memName,memgroupname;
    Character memImage;


    public MembersAdded(String memName, Character memImage, String memgroupname) {
        this.memImage=memImage;
        this.memName=memName;
        this.memgroupname=memgroupname;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getMemgroupname() {
        return memgroupname;
    }

    public void setMemgroupname(String memgroupname) {
        this.memgroupname = memgroupname;
    }

    public Character getMemImage() {
        return memImage;
    }

    public void setMemImage(Character memImage) {
        this.memImage = memImage;
    }
}
