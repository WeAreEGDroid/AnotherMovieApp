package com.egdroid.datasource.local.movie

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    companion object {
        @TypeConverter
        @JvmStatic
        fun fromIntList(list: List<Int>?): String? {
            if (list == null) {
                return null
            }
            val type = object : TypeToken<List<Int>>() {}.type
            return Gson().toJson(list, type)
        }

        @TypeConverter
        @JvmStatic
        fun toIntList(listString: String?): List<Int>? {
            if (listString == null) {
                return null
            }
            val type = object : TypeToken<List<Int>>() {}.type
            return Gson().fromJson<List<Int>>(listString, type)
        }
    }
}