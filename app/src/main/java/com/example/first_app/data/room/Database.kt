package com.example.first_app.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.first_app.data.room.converters.TimeStampConverter
import com.example.first_app.data.room.models.Visitor

@TypeConverters(TimeStampConverter::class)
@Database(
    entities = [Visitor::class], // for create tables"
    version = 1, // is for database version, when we want to migrate
    exportSchema = false
    )
abstract class CheckInDB:RoomDatabase() {
    abstract fun visitorDao(): VisitorDao

    companion object{
        @Volatile
        var INSTANCE:CheckInDB? = null
        fun getDatabase(context: Context): CheckInDB{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CheckInDB::class.java,
                    "check_in_db"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }
}