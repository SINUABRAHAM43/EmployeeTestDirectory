package com.example.employeedirectory

import android.app.Application
import com.example.employeedirectory.data.database.EmployeeDatabase
import com.example.employeedirectory.data.database.Employeerepository

class myApplication  : Application() {
    val database by lazy { EmployeeDatabase.getDatabase(this) }
    val repository by lazy { Employeerepository(database.employeeDao()) }
}
