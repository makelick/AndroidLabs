package com.kpi.labs.lab3.database

import android.content.Context
import androidx.room.RoomDatabase

@androidx.room.Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun getDao(): Dao

    companion object {

        @Volatile
        private var INSTANCE: Database? = null

        fun getDatabase(context: Context): Database {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "result_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}