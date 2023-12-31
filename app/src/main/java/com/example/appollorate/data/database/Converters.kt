package com.example.appollorate.data.database

import androidx.room.TypeConverter
import com.example.appollorate.data.model.DropDownValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    object DropDownValueTypeConverter {
        @TypeConverter
        @JvmStatic
        fun stringToDropDownValue(value: String): DropDownValue =
            Gson().fromJson(value, object : TypeToken<DropDownValue>() {}.type)

        @TypeConverter
        @JvmStatic
        fun dropDownValueToString(value: DropDownValue?): String =
            if (value == null) "" else Gson().toJson(value)
    }

    object ListTypeConverter {
        @TypeConverter
        @JvmStatic
        fun stringToList(value: String): List<DropDownValue>? =
            Gson().fromJson(value, object : TypeToken<List<DropDownValue>?>() {}.type)

        @TypeConverter
        @JvmStatic
        fun listToString(value: List<DropDownValue>?): String =
            if (value == null) "" else Gson().toJson(value)
    }
}
