package com.example.employeedirectory.data.api;

import com.example.employeedirectory.data.model.Employee;
import io.reactivex.Single

interface ApiService {
    fun getEmployees(): Single<List<Employee>>

}
