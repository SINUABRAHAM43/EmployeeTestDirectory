package com.example.employeedirectory

import android.app.Application
import com.example.employeedirectory.data.api.ApiHelper
import com.example.employeedirectory.data.api.ApiServiceImpl
import com.example.employeedirectory.data.database.EmployeeDatabase
import com.example.employeedirectory.data.repository.EmployeeRepository

class myApplication  : Application() {
    val database by lazy { EmployeeDatabase.getDatabase(this) }
    val repository by lazy { EmployeeRepository(ApiHelper(ApiServiceImpl()),employeeDao = database.employeeDao()) }
}
