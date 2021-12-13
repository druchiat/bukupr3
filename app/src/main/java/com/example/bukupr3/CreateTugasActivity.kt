package com.example.bukupr3

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.button.MaterialButton

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

        class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
    }
}