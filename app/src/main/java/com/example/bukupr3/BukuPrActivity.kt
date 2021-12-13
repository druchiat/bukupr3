package com.example.bukupr3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bukupr3.model.BukuPr
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class BukuPrActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private var bukuPr : ArrayList<BukuPr> = ArrayList()
    private lateinit var rvBukuPr: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buku_pr)

        val btnLogout : Button = findViewById(R.id.btnLogout)
        val btnCreate : Button = findViewById(R.id.btnCreateBukuPr)
        rvBukuPr = findViewById(R.id.rvBukuPr)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        getData(Firebase.auth.currentUser?.uid.toString())

        btnCreate.setOnClickListener {
            startActivity(Intent(this, CreateBukuPrActivity::class.java))
            finish()
        }

        btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }


    }

    private fun getData(uid : String){
        db.collection("BukuPr")
            .whereEqualTo("uid", uid)
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    bukuPr.add(BukuPr(
                        uid = document.get("uid").toString(),
                        idBukuPr = document.get("idBukuPr").toString(),
                        namaBukuPr = document.get("namaBukuPr").toString()
                    ))
                }
                val layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
                val adapterBukuPr = AdapterBukuPr()
                bukuPr.sortBy { it.namaBukuPr }
                adapterBukuPr.bukuPr = bukuPr
                rvBukuPr.layoutManager = layoutManager
                rvBukuPr.adapter = adapterBukuPr
                rvBukuPr.setHasFixedSize(true)

                Log.d("Get Data Buku Pr", "Get Data Success")
            }
            .addOnFailureListener {
                Log.e("Get Data Buku Pr", "Get Data Failed", it )
            }
    }

}