package com.example.employeedirectory.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeedirectory.data.model.Employee
import com.example.employeedirectory.data.repository.EmployeeRepository
import com.example.employeedirectory.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EmployeeViewModel (private val mainRepository: EmployeeRepository) : ViewModel() {

    private val users = MutableLiveData<Resource<List<Employee>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchEmployees()
    }

    private fun fetchEmployees() {
        users.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    users.postValue(Resource.success(userList))
                }, { throwable ->
                    users.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getEmployees(): LiveData<Resource<List<Employee>>> {
        return users
    }
    fun search(query:String,employees:ArrayList<Employee>): List<Employee> {
        val users=ArrayList<Employee>()
        employees.forEach{
         if(it.name?.contains(query,false) == true ||(it.email?.contains(query,false) == true))   {
             users.add(it)
         }
        }

        return users
    }





}
