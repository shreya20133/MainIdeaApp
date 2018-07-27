package com.example.dell1.mainideaapp;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {

    private static Gson gson = new Gson();
    @TypeConverter
    public static List<GroupMembers> stringToList(String data) {

        Type listType = new TypeToken<List<GroupMembers>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<GroupMembers> someObjects) {
        return gson.toJson(someObjects);
    }
}
