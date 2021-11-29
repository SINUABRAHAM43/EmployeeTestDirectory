package com.example.employeedirectory.data.database

import android.os.Parcel
import android.os.Parcelable
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
                      @ColumnInfo(name = "zipcode")   val zipcode: String?,
                      @ColumnInfo(name = "phone")   val phone: String?,
                      @ColumnInfo(name = "website")   val website: String?,
                      @ColumnInfo(name = "company_name") val company_name: String?

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeString(profile_image)
        parcel.writeString(street)
        parcel.writeString(suite)
        parcel.writeString(city)
        parcel.writeString(zipcode)
        parcel.writeString(phone)
        parcel.writeString(website)
        parcel.writeString(company_name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EmployeeEntity> {
        override fun createFromParcel(parcel: Parcel): EmployeeEntity {
            return EmployeeEntity(parcel)
        }

        override fun newArray(size: Int): Array<EmployeeEntity?> {
            return arrayOfNulls(size)
        }
    }
}
