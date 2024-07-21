package com.example.first_app.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.first_app.data.room.models.Visitor
import kotlinx.coroutines.flow.Flow


@Dao
interface VisitorDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE) // dans le cas où on mm id, va le remplacer par .REPLACE et on peut ignorer par IGNORE
    suspend fun insert(visitor: Visitor)

    @Query("SELECT * FROM visitor")
    fun getAllVisitors(): Flow<List<Visitor>>

    @Query("SELECT * FROM visitor WHERE tag =:tag")
    fun getVisitorsByTag(tag: String): Flow<List<Visitor>>
}