package com.example.bukupr3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bukupr3.model.BukuPr
import com.example.bukupr3.model.Tugas
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class TugasActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private var tugas : ArrayList<Tugas> = ArrayList()
    private lateinit var rvTugas: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tugas)

        val dataBukuPr = intent.getParcelableExtra<BukuPr>("bukupr")
        val btnCreateTugas : Button = findViewById(R.id.btnCreateTugas)
        val btnBackToBukuPr : Button = findViewById(R.id.btnBackToBukuPr)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        getDataTugas(dataBukuPr?.idBukuPr)

        btnCreateTugas.setOnClickListener {
            val intent = Intent(this, CreateTugasActivity::class.java)
            intent.putExtra("idBukuPr", dataBukuPr?.idBukuPr)
            startActivity(intent)
            finish()
        }


    }

    private fun getDataTugas(idBukuPr: String?) {
        db.collection("Tugas")
            .whereEqualTo("idBukuPr", idBukuPr)
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    tugas.add(Tugas(
                        idTugas = document.get("idTugas").toString(),
                        idBukuPr = document.get("idBukuPr").toString(),
                        namaTugas = document.get("namaTugas").toString(),
                        deskripsi = document.get("deskripsi").toString(),
                        subject = document.get("subject").toString(),
                        startDate = document.get("startDate").toString(),
                        dueDate = document.get("dueDate").toString(),
                        status = document.get("status").toString()
                    ))
                }
                val layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
                val adapterTugas = AdapterTugas()
                tugas.sortBy { it.subject }
                adapterTugas.tugas = tugas
                rvTugas.layoutManager = layoutManager
                rvTugas.adapter = adapterTugas
                rvTugas.setHasFixedSize(true)

                Log.d("Get Data Tugas", "Get Data Success")
            }
            .addOnFailureListener {
                Log.e("Get Data Tugas", "Get Data Failed", it )
            }
    }
}