package com.example.employeedirectory.data.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.employeedirectory.data.model.Address
import com.example.employeedirectory.data.model.Employee

interface EmployeeDao {

    @Query("SELECT * FROM Employee")
    suspend fun getAll(): List<Employee>

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insert(employees: List<Employee>)

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insert(address:Address)



    @Delete
    suspend fun delete(employee: Employee)
}