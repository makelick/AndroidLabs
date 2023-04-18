package com.kpi.labs.lab3

import android.app.Application
import com.kpi.labs.lab3.database.Database

class Lab3Application : Application() {
    val database by lazy { Database.getDatabase(this) }
}