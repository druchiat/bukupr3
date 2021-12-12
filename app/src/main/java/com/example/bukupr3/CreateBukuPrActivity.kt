package com.example.bukupr3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text

class CreateBukuPrActivity : AppCompatActivity() {

    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_buku_pr)

        val mbBackCBP : MaterialButton = findViewById(R.id.mbBackCBP)
        val mbDoneCBP : MaterialButton = findViewById(R.id.mbDoneCBP)
        val etNamaBukuPr : EditText = findViewById(R.id.etNamaBukuPr)

        db = FirebaseFirestore.getInstance()

        mbBackCBP.setOnClickListener {
            startActivity(Intent(this,BukuPrActivity::class.java))
            finish()
        }

        mbDoneCBP.setOnClickListener {
            val nama = etNamaBukuPr.text.toString()
            if (TextUtils.isEmpty(nama)){
                etNamaBukuPr.error = "Enter name here"
                return@setOnClickListener
            }
            if (nama.length > 20) {
                etNamaBukuPr.error = "Maximum of 20 characters"
                return@setOnClickListener
            }
            addBukuPr(nama)
        }

    }

    private fun addBukuPr(nama : String){

    }

}