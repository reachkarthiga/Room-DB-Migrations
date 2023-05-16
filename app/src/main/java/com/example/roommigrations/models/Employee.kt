package com.example.roommigrations.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


// Version 1

//@Entity(tableName = "employee")
//@Parcelize
//data class Employee(
//    val name:String,
//    val age:String,
//    val gender:String,
//    val salary: Int,
//    val designation:String,
//    @PrimaryKey
//    val id:Long = System.currentTimeMillis()
//) :Parcelable


// Version 2
//@Entity(tableName = "employee")
//@Parcelize
//data class Employee(
//    val name:String,
//    val age:String,
//    val gender:String,
//    val salary: Int,
//    val designation:String,
//    @PrimaryKey
//    val id:Long = System.currentTimeMillis()
//) :Parcelable
//

//Version 3
@Entity(tableName = "employee")
@Parcelize
data class Employee(
    val name:String,
    val age:Int,
    val gender:String,
    val salary: Int,
    val designation:String,
    @PrimaryKey
    val id:Long = System.currentTimeMillis()
) :Parcelable


