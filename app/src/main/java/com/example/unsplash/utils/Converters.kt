package com.example.unsplash.utils

import androidx.room.TypeConverter
import com.example.unsplash.db.models.Src

class Converters {

    @TypeConverter
    fun toSrc(medium: String) : Src {
        return Src(medium,medium,medium,medium,medium,medium,medium,medium)
    }

    @TypeConverter
    fun fromSrc(src:Src) :String {
       return src.medium
    }

}