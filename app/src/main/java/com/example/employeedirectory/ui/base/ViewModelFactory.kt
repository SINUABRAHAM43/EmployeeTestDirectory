package com.example.employeedirectory.ui.base;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.employeedirectory.data.api.ApiHelper;
import com.example.employeedirectory.data.repository.EmployeeRepository
import com.example.employeedirectory.ui.main.viewmodel.EmployeeViewModel

class ViewModelFactory(private val repository: EmployeeRepository) : ViewModelProvider.Factory {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
      return EmployeeViewModel(repository) as T
    }
    throw IllegalArgumentException("Unknown class name")
  }

}