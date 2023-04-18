package com.kpi.labs.lab3.database

import androidx.room.Insert
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    suspend fun insert(result: Result)

    @androidx.room.Query("SELECT * FROM result ORDER BY id")
    fun getAll(): Flow<List<Result>>

}