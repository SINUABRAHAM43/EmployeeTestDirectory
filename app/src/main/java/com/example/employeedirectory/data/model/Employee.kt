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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Address::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Company::class.java.classLoader)
    ) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeString(profile_image)
        parcel.writeParcelable(address, flags)
        parcel.writeString(phone)
        parcel.writeString(website)
        parcel.writeParcelable(company, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Employee> {
        override fun createFromParcel(parcel: Parcel): Employee {
            return Employee(parcel)
        }

        override fun newArray(size: Int): Array<Employee?> {
            return arrayOfNulls(size)
        }
    }
}

data class Company(
    val name: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()
    ) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Company> {
        override fun createFromParcel(parcel: Parcel): Company {
            return Company(parcel)
        }

        override fun newArray(size: Int): Array<Company?> {
            return arrayOfNulls(size)

        }
    }

}

data class Address(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    val geo: GeoLocation?

) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(GeoLocation::class.java.classLoader)
    ) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(street)
        parcel.writeString(suite)
        parcel.writeString(city)
        parcel.writeString(zipcode)
        parcel.writeParcelable(geo, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Address> {
        override fun createFromParcel(parcel: Parcel): Address {
            return Address(parcel)
        }

        override fun newArray(size: Int): Array<Address?> {
            return arrayOfNulls(size)
        }
    }
}


data class GeoLocation(
    val lat: String?,
    val lng: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()

    ) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(lat)
        parcel.writeString(lng)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GeoLocation> {
        override fun createFromParcel(parcel: Parcel): GeoLocation {
            return GeoLocation(parcel)
        }

        override fun newArray(size: Int): Array<GeoLocation?> {
            return arrayOfNulls(size)
        }
    }
}
