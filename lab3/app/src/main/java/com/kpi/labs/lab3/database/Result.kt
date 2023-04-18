package com.kpi.labs.lab3.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val utensil: String,
    val brands: String
)