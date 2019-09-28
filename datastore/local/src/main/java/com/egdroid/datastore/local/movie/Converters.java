package com.egdroid.datastore.local.movie;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {

    //This method takes our arrayList object as parameter and returns string representation for it so
    // that it can be stored in Room Database
    @TypeConverter
    public static String writingStringFromList(List<Integer> list) {
        String genreIds = "";
        for (int i : list) {
            genreIds += "," + i;
        }
        return genreIds;
    }

    //While reading data back from Room Database, we get String form our arrayList which we need to convert back
// so we need to provide the class of original object (in our case, List)
    @TypeConverter
    public static List<Integer> gettingListFromString(String genreIds) {
        List<Integer> list = new ArrayList<>();

        String[] array = genreIds.split(",");

        for (String s : array) {
            if (!s.isEmpty()) {
                list.add(Integer.parseInt(s));
            }
        }
        return list;
    }

}
