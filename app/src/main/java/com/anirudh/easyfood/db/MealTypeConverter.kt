package com.anirudh.easyfood.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.jar.Attributes

@TypeConverters
class MealTypeConverter {

    @TypeConverter
    fun fromAnyToString(attribute: Any?): String {
        if (attribute == null) {
            return ""
        }
        return attribute as String
    }

    @TypeConverter
    fun fromStringToAny(attribute: Any?): Any {
        if (attribute == null) {
            return ""
        }
        return attribute
    }

}