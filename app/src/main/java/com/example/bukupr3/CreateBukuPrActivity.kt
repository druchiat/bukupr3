package com.example.bukupr3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bukupr3.model.BukuPr
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
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
            val currentUser = Firebase.auth.currentUser?.uid.toString()
            val bukupr = BukuPr(currentUser, nama)

            addBukuPr(bukupr)
        }

    }

    private fun addBukuPr(bukupr : BukuPr){
        db.collection("BukuPr").add(bukupr)
            .addOnSuccessListener {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, BukuPrActivity::class.java))
                finish()
            } .addOnFailureListener {
                Toast.makeText(this, "Failed. Please try again.", Toast.LENGTH_LONG).show()
            }

    }

}