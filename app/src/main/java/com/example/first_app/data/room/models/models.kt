package com.example.first_app.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.time.format.DateTimeFormatter


@Entity(tableName = "visitor")

data class Visitor(
    @ColumnInfo("visitor_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("firstname")
    val firstname: String,
    @ColumnInfo("lastname")
    val lastname: String,
    @ColumnInfo("tag")
    val tag: String,
    @ColumnInfo("check_in_time")
    val checkInTime: Timestamp = Timestamp(System.currentTimeMillis())
)