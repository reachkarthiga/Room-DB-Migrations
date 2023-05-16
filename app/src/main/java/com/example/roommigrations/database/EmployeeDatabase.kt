package com.example.roommigrations.database

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roommigrations.models.Employee


@Database(entities = [Employee::class] , version = 3, exportSchema = false)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract val dao: EmployeeDao

    companion object {

        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("CREATE TABLE employeeNew (name TEXT NOT NULL, designation TEXT NOT NULL,id INTEGER NOT NULL, gender TEXT NOT NULL, salary INTEGER NOT NULL,  age TEXT NOT NULL, PRIMARY KEY(id))")
                database.execSQL("INSERT INTO employeeNew (name,designation,id,  gender, salary, age) Select name ,designation, id , case when gender = \"FEMALE\" then \"F\" when gender = \"MALE\" then \"M\"  else \"O\" end as gender, salary, age  from employee")
                database.execSQL("DROP TABLE employee")
                database.execSQL("ALTER TABLE employeeNew RENAME TO employee")
            }
        }

            val migration_2_3 = object : Migration(2, 3) {
                override fun migrate(database: SupportSQLiteDatabase) {

                    database.execSQL("CREATE TABLE employeeNew (name TEXT NOT NULL, designation TEXT NOT NULL,id INTEGER NOT NULL, gender TEXT NOT NULL, salary INTEGER NOT NULL,  age INTEGER NOT NULL, PRIMARY KEY(id))")
                    database.execSQL("INSERT INTO employeeNew (name,designation,id, gender, salary, age) Select name ,designation, id , gender, salary, case when  age REGEXP \'^[0-9]+\$\' then  age else 0 end as age  from employee")
                    database.execSQL("DROP TABLE employee")
                    database.execSQL("ALTER TABLE employeeNew RENAME TO employee")

                }

            }

                val migration_1_3 = object : Migration(1, 3) {
                    override fun migrate(database: SupportSQLiteDatabase) {

                        database.execSQL("CREATE TABLE employeeNew (name TEXT NOT NULL, designation TEXT NOT NULL,id INTEGER NOT NULL, gender TEXT NOT NULL, salary INTEGER NOT NULL,  age INTEGER NOT NULL, PRIMARY KEY(id))")
                        database.execSQL("INSERT INTO employeeNew (name,designation,id, gender, salary, age) Select name ,designation, id , case when gender = \"FEMALE\" then \"F\" when gender = \"MALE\" then \"M\"  else \"O\" end as gender, salary, case when  age REGEXP \'^[0-9]+\$\' then  age else 0 end as age  from employee")
                        database.execSQL("DROP TABLE employee")
                        database.execSQL("ALTER TABLE employeeNew RENAME TO employee")

                    }

                }

        }

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
                    ).allowMainThreadQueries().addMigrations(migration_1_2, migration_2_3, migration_1_3)
                        .build()

                    INSTANCE = instance

                }

                return instance

            }

        }
    }



}