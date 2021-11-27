package com.example.employeedirectory.data.database

import androidx.room.*

@Entity(tableName = "Employee")
class EmployeeEntity( @PrimaryKey(autoGenerate = false)
                      val id: Int,
                      @ColumnInfo(name = "name") val name: String?,
                      @ColumnInfo(name = "username") val username: String?,
                      @ColumnInfo(name = "email") val email: String?,
                      @ColumnInfo(name = "profile_image") val profile_image: String?,
                      @ColumnInfo(name = "Street")  val street: String?,
                      @ColumnInfo(name = "suite")    val suite: String?,
                      @ColumnInfo(name = "city")   val city: String?,
                      @ColumnInfo(name = "zipcode")   val zipcode: String,
                      @ColumnInfo(name = "phone")   val phone: String,
                      @ColumnInfo(name = "website")   val website: String,
                      @ColumnInfo(name = "company_name") val company_name: String?

)
