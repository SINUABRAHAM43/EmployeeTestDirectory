package com.example.employeedirectory.data.api

class ApiHelper(private val apiService: ApiService) {
    fun getEmployees() = apiService.getEmployees()

}