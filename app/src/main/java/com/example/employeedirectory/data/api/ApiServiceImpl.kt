package com.example.employeedirectory.data.api

import com.example.employeedirectory.data.model.Employee
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single


class ApiServiceImpl : ApiService {

    override fun getEmployees(): Single<List<Employee>> {
        return Rx2AndroidNetworking.get("https://www.mocky.io/v2/5d565297300000680030a986")
            .build()
            .getObjectListSingle(Employee::class.java)
    }

}