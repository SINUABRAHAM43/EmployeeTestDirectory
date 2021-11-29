package com.example.employeedirectory.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeedirectory.data.database.EmployeeEntity
import com.example.employeedirectory.databinding.ActivityMainBinding
import com.example.employeedirectory.myApplication
import com.example.employeedirectory.ui.base.ViewModelFactory
import com.example.employeedirectory.ui.main.adapter.EmployeeListAdapter
import com.example.employeedirectory.ui.main.viewmodel.EmployeeViewModel
import com.example.employeedirectory.utils.Status


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: EmployeeListAdapter
    private lateinit var mainViewModel: EmployeeViewModel
    private var employees = ArrayList<EmployeeEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        adapter = EmployeeListAdapter(arrayListOf(), listener = { employee ->
            val intent = Intent(this, EmployeeDetails::class.java)
            intent.putExtra("Employee", employee)
            startActivity(intent)
        })
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
        binding.search.setOnQueryTextListener(this)

    }

    private fun setupObserver() {
        mainViewModel.getEmployees().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { users ->
                        employees = users as ArrayList<EmployeeEntity>
                        renderList(users)
                    }
                    binding.search.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.search.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    private fun renderList(users: List<EmployeeEntity>) {
        adapter.addData(users)
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory((application as myApplication).repository)
        ).get(EmployeeViewModel::class.java)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            val users = mainViewModel.search(query, employees)
            renderList(users)
        } else {
            renderList(employees)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrEmpty()) {
            val users = mainViewModel.search(newText, employees)
            renderList(users)
        } else {
            renderList(employees)
        }
        return true
    }

}


