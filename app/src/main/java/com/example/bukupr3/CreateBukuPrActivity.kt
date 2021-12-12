package com.example.bukupr3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text

class CreateBukuPrActivity : AppCompatActivity() {

    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_buku_pr)


        val etNamaBukuPr : EditText = findViewById(R.id.etNamaBukuPr)

        db = FirebaseFirestore.getInstance()



    }



}