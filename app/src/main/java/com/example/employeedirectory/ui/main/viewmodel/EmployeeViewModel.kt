package com.example.employeedirectory.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeedirectory.data.database.EmployeeEntity
import com.example.employeedirectory.data.repository.EmployeeRepository
import com.example.employeedirectory.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class EmployeeViewModel(private val mainRepository: EmployeeRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private var users = MutableLiveData<Resource<List<EmployeeEntity>>>()

    init {
        fetchEmployees()
    }

    private fun fetchEmployees() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                val usersFromDb = mainRepository.allEmployees
                if (usersFromDb.isEmpty()) {

                    val usersToInsertInDB = mutableListOf<EmployeeEntity>()
                    compositeDisposable.add(
                        mainRepository.getEmployees()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ userList ->
                                for (apiUser in userList) {

                                    val address = apiUser.address
                                    val company = apiUser.company
                                    val employeeData = EmployeeEntity(
                                        id = apiUser.id,
                                        name = apiUser.name,
                                        username = apiUser.username,
                                        email = apiUser.email,
                                        profile_image = apiUser.profile_image,
                                        street = address?.street ?: "",
                                        suite = address?.suite ?: "",
                                        city = address?.city ?: "",
                                        zipcode = address?.zipcode ?: "",
                                        phone = apiUser.phone ?: "",
                                        website = apiUser.website ?: "",
                                        company_name = company?.name ?: ""
                                    )
                                    viewModelScope.launch {
                                        mainRepository.insert(employeeData)
                                    }

                                    usersToInsertInDB.add(employeeData)
                                }
                                users.postValue(Resource.success(usersToInsertInDB))

                            }, { throwable ->
                                users.postValue(Resource.error("Something Went Wrong", null))
                            })
                    )


                } else {
                    users.postValue(Resource.success(usersFromDb))
                }

            } catch (e: Exception) {
                users.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }


    fun getEmployees(): LiveData<Resource<List<EmployeeEntity>>> {
        return users
    }

    fun search(query: String, employees: ArrayList<EmployeeEntity>): List<EmployeeEntity> {
        val users = ArrayList<EmployeeEntity>()
        employees.forEach {
            if (it.name?.contains(query, false) == true || (it.email?.contains(
                    query,
                    false
                ) == true)
            ) {
                users.add(it)
            }
        }

        return users
    }


}
