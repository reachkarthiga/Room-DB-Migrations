package com.example.roommigrations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.roommigrations.database.EmployeeDatabase
import com.example.roommigrations.models.Employee
import kotlinx.android.synthetic.main.add_employee.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddEmployee : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_employee)

        val database = EmployeeDatabase.getInstance(this)

        save_button.setOnClickListener {

            if (!validateData()) {
                CoroutineScope(Dispatchers.IO).launch {
                    database.dao.insertEmployee(
                        Employee(
                            editText_name.text.toString(),
                            editText_age.text.toString(),
                            editText_gender.text.toString(),
                            editText_salary.text.toString().toInt(),
                            editText_designation.text.toString()
                        )

                    )
                }

                startActivity(Intent(this, MainActivity::class.java))
                finish()

            }


        }


    }

    private fun validateData(): Boolean {

        var isError = false

        if (editText_name.text.trim().isEmpty()) {
            sendToast("Name cannot be blank")
            isError = true
        }

        if (editText_age.text.trim().isEmpty()) {
            sendToast("Age cannot be blank")
            isError = true
        }

        if (editText_gender.text.trim().isEmpty()) {
            sendToast("Age cannot be blank")
            isError = true
        }

        if (!(editText_gender.text.trim().toString().toUpperCase() == "FEMALE" ||
            editText_gender.text.trim().toString().toUpperCase() == "MALE" ||
            editText_gender.text.trim().toString().toUpperCase() == "OTHERS" ))
        {
            sendToast("Gender is not valid")
            isError = true
        }

        if (editText_salary.text.isEmpty()) {
            sendToast("Age cannot be blank")
            isError = true
        }

        if (editText_designation.text.isEmpty()) {
            sendToast("Age cannot be blank")
            isError = true
        }

        return isError
    }

    private fun sendToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}