package com.example.roommigrations.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roommigrations.models.Employee


@Database(entities = [Employee::class], version = 1, exportSchema = true)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract val dao: EmployeeDao

    companion object {
        @Volatile
        private var INSTANCE: EmployeeDatabase? = null

        fun getInstance(context: Context): EmployeeDatabase {


            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EmployeeDatabase::class.java,
                        "EmployeeDetails"
                    ).allowMainThreadQueries()
                        .build()

                    INSTANCE = instance

                }

                return instance

            }

        }
    }

}