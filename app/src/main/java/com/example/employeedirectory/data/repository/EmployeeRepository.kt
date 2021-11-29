package com.example.employeedirectory.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.employeedirectory.data.api.ApiHelper
import com.example.employeedirectory.data.database.EmployeeDao
import com.example.employeedirectory.data.database.EmployeeEntity
import com.example.employeedirectory.data.model.Employee
import io.reactivex.Single

class EmployeeRepository(private val apiHelper: ApiHelper, private val employeeDao: EmployeeDao) {

    fun getEmployees(): Single<List<Employee>> {
        return apiHelper.getEmployees()
    }
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allEmployees: List<EmployeeEntity> = employeeDao.getAllEmployees()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun insert(employee: EmployeeEntity) {
        Log.e("insert",employee.toString())
        employeeDao.insert(employee)
    }
}