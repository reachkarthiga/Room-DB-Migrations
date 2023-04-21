package com.example.roommigrations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roommigrations.database.EmployeeDatabase
import com.example.roommigrations.models.Employee
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = EmployeeDatabase.getInstance(this)
        val list = database.dao.getDetails()

        add_new.setOnClickListener {
            startActivity(Intent(this, AddEmployee::class.java))
        }

        if (list.isEmpty()) {
            no_data.visibility = VISIBLE
        } else {
            no_data.visibility = GONE
        }

        val adapter = Adapter(list)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

}