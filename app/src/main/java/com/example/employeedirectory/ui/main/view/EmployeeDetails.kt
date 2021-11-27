package com.example.employeedirectory.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.employeedirectory.data.model.Employee
import com.example.employeedirectory.databinding.ActivityEmployeeDetailsBinding
import kotlinx.android.synthetic.main.item_employee.view.*

class EmployeeDetails : AppCompatActivity() {
    private lateinit var binding: ActivityEmployeeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val employee = intent?.getParcelableExtra<Employee>("Employee")
        setData(employee)
    }

    private fun setData(employee: Employee?) {
        employee?.let {
            Glide.with(this)
                .load(employee.profile_image)
                .into(binding.profilePhoto)

            binding.fullName.text=employee.name
            binding.userName.text=employee.username
            binding.email.text=employee.email
            binding.website.text=employee.website
            binding.phone.text=employee.phone?:"N/A"
            employee.company?.name?.let {
                binding.companyDetails.text=it
            }?:kotlin.run{
                binding.companyDetails.text="N/A"
            }
            employee.address?.let {add->
                val address=add.suite+"\n"+add.street+"\n"+add.city
                binding.addresss.text=address
            }

        }
    }

}