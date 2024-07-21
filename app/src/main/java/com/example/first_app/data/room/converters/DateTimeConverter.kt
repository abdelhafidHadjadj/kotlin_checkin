package com.example.first_app.data.room.converters

import androidx.room.TypeConverter
import java.sql.Timestamp

open class TimeStampConverter{
    @TypeConverter
    fun fromTimestamp(value: Long?): Timestamp? {
        return value?.let { Timestamp(it) }
    }

    @TypeConverter
    fun dateToTimestamp(timestamp: Timestamp?): Long? {
        return timestamp?.time
    }
}
