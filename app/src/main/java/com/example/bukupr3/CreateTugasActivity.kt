package com.example.bukupr3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*

class CreateTugasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_tugas)

        val spinner: Spinner = findViewById(R.id.status_spinner)
        val btnBackCT : Button = findViewById(R.id.btnBackCT)
        val btnDoneCT : Button = findViewById(R.id.btnDoneCT)
        val etNamaTugas : EditText = findViewById(R.id.etNamaTugas)
        val etDescription : EditText = findViewById(R.id.etDescription)
        val status_spinner : Spinner = findViewById(R.id.status_spinner)
        val etSubject : EditText = findViewById(R.id.etSubject)
        val etStartDate : EditText = findViewById(R.id.etStartDate)
        val etDueDate : EditText = findViewById(R.id.etDueDate)

        btnBackCT.setOnClickListener {
            startActivity(Intent(this,TugasActivity::class.java))
            finish()
        }

        btnDoneCT.setOnClickListener {
            val nama = etNamaTugas.text.toString()
            val desc = etDescription.text.toString()
            //val status =
            val subject = etSubject.text.toString()
            val startDate = etStartDate.text.toString()
            val dueDate = etDueDate.text.toString()

            if (TextUtils.isEmpty(nama)) {
                etNamaTugas.error = "Enter name here"
            }
            if (TextUtils.isEmpty(desc)) {
                etDescription.error = "Enter Description here"
            }
            if (TextUtils.isEmpty(subject)) {
                etSubject.error = "Enter Subject here"
            }
            if (TextUtils.isEmpty(startDate)) {
                etStartDate.error = "Enter Start Date here"
            }
            if (TextUtils.isEmpty(dueDate)) {
                etDueDate.error = "Enter Due Date here"
            }

        }


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.status_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

    }
}