package com.example.bukupr3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bukupr3.model.BukuPr

class TugasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tugas)

        val dataBukuPr = intent.getParcelableExtra<BukuPr>("bukupr")


    }
}