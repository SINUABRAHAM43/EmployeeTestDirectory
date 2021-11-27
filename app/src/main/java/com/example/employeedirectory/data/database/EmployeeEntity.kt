package com.example.employeedirectory.data.database

import androidx.room.*


@Entity(tableName = "Employee")
data class EmployeeEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "profile_image") val profile_image: String?,
    @ColumnInfo(name = "employeeAddressId") val employeeAddressId: Int?
)
@Entity(tableName = "Address")
  data class AddressEntity (
      @PrimaryKey(autoGenerate = true)
      @ColumnInfo(name = "addressId")  val addressId: Int,
      @ColumnInfo(name = "Street")  val street: String?,
      @ColumnInfo(name = "suite")    val suite: String?,
      @ColumnInfo(name = "city")   val city: String?,
      @ColumnInfo(name = "zipcode")   val zipcode: String
      )
@Entity(tableName = "Company")
data class CompanyEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CompanyId")  val CompanyId: Int,
    @ColumnInfo(name = "name")  val name: String?,
)

@Entity(tableName = "EmployeeAddress")
data class EmployeeAddress(
    @Embedded val employee: EmployeeEntity,
    @Relation(
        parentColumn = "employeeAddressId",
        entityColumn = "addressId"
    )
    val address: AddressEntity
)
