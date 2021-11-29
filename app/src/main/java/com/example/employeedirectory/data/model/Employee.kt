package com.example.employeedirectory.data.model

import android.os.Parcel
import android.os.Parcelable

data class Employee(
    val id: Int,
    val name: String?,
    val username: String?,
    val email: String?,
    val profile_image: String?,
    val address: Address?,
    val phone: String?,
    val website: String?,
    val company: Company?
)

data class Company(
    val name: String?
)

data class Address(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    val geo: GeoLocation?

)
data class GeoLocation(
    val lat: String?,
    val lng: String?
)
