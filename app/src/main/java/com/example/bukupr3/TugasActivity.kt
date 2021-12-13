package com.example.bukupr3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.bukupr3.model.BukuPr

class TugasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tugas)

        val dataBukuPr = intent.getParcelableExtra<BukuPr>("bukupr")
        val btnCreateTugas : Button = findViewById(R.id.btnCreateTugas)

        btnCreateTugas.setOnClickListener {
            startActivity(Intent(this, CreateTugasActivity::class.java))
            finish()
        }


    }
}