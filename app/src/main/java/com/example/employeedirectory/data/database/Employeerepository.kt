package com.example.employeedirectory.data.database

import androidx.annotation.WorkerThread

class Employeerepository(private val employeeDao: EmployeeDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allEmployees: List<EmployeeEntity> = employeeDao.getAllEmployees()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun insert(employee: EmployeeEntity) {
        employeeDao.insert(employee)
    }
}