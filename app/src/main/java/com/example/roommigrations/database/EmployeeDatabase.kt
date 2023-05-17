package com.example.roommigrations.database

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import com.example.roommigrations.models.Employee


@Database(entities = [Employee::class], version = 3, exportSchema = true, autoMigrations = [AutoMigration(from = 1, to = 2, spec = EmployeeDatabase.autoMigrationSpecVersion2::class), AutoMigration(from = 2, to = 3)])
abstract class EmployeeDatabase : RoomDatabase() {

    abstract val dao: EmployeeDao

    @RenameColumn(tableName = "employee", fromColumnName = "name", toColumnName = "employeeName")
    @DeleteColumn(tableName = "employee" , columnName = "age")
    class autoMigrationSpecVersion2: AutoMigrationSpec{}

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