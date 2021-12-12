package com.example.bukupr3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmailLogin : EditText = findViewById(R.id.etEmailLogin)
        val etPasswordLogin : EditText = findViewById(R.id.etPasswordLogin)
        val btnLogin : Button = findViewById(R.id.btnLogin)
        val tvToSignUp : TextView = findViewById(R.id.tvToSignup)
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            val email = etEmailLogin.text.toString()
            val password = etPasswordLogin.text.toString()

            if (TextUtils.isEmpty(email)) {
                etEmailLogin.error = "Enter email here"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                etPasswordLogin.error = "Enter password here"
                return@setOnClickListener
            }
            login(email, password)
        }

        tvToSignUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

    }

    private fun login(email : String, password : String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, CreateBukuPrActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                Log.e("Login", "Login Failed", e)
                Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
            }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            startActivity(Intent(this, CreateBukuPrActivity::class.java))
            finish()
        }
    }
}