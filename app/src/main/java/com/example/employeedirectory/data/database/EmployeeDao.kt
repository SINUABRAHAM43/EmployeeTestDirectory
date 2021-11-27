package com.example.employeedirectory.data.database

import androidx.room.*

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM Employee")
    fun getAllEmployees(): List<EmployeeEntity>

    @Insert
  fun insert(employee: EmployeeEntity)

}