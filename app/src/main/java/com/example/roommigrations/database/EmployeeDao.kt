package com.example.roommigrations.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roommigrations.models.Employee

@Dao
interface EmployeeDao {

    @Query( "SELECT * FROM employee")
    fun getDetails() : List<Employee>

    @Insert
    suspend fun insertEmployee(employee: Employee) :Unit

}