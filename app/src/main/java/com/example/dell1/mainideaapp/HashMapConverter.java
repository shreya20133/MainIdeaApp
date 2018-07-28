package com.example.dell1.mainideaapp;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;


public class HashMapConverter  {

    private static Gson gson = new Gson();
    @TypeConverter
    public static HashMap<GroupMembers,Double> stringToList(String data) {

        Type listType = new TypeToken<HashMap<GroupMembers,Double>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(HashMap<GroupMembers,Double> someObjects) {
        return gson.toJson(someObjects);
    }
}
