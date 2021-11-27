package com.example.employeedirectory.data.repository

import com.example.employeedirectory.data.api.ApiHelper
import com.example.employeedirectory.data.model.Employee
import io.reactivex.Single

class EmployeeRepository (private val apiHelper: ApiHelper) {

    fun getEmployees(): Single<List<Employee>> {
        return apiHelper.getEmployees()
    }

}