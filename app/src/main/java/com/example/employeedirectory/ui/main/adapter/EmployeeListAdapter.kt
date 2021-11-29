package com.example.employeedirectory.ui.main.adapter

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.employeedirectory.R
import com.example.employeedirectory.data.database.EmployeeEntity
import com.example.employeedirectory.data.model.Employee
import kotlinx.android.synthetic.main.item_employee.view.*

class EmployeeListAdapter (
    private val Employees: ArrayList<EmployeeEntity>,val listener: (EmployeeEntity) -> Unit

) : RecyclerView.Adapter<EmployeeListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(employee: EmployeeEntity) {
            itemView.tv_employee_name.text = employee.name
            itemView.tv_company_name.text = employee.company_name
            Glide.with(itemView.profile_image.context)
                .load(employee.profile_image)
                .into(itemView.profile_image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_employee, parent,
                false
            )
        )

    override fun getItemCount(): Int = Employees.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(Employees[position])

        holder.itemView.setOnClickListener {
            listener(Employees[position])
        }
    }

    fun addData(list: List<EmployeeEntity>) {
        Employees.clear()
        Employees.addAll(list)
        notifyDataSetChanged()
    }

}