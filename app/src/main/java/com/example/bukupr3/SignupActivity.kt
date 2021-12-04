package com.example.bukupr3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.bukupr3.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*

class SignupActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val etEmailSignup : EditText = findViewById(R.id.etEmailSignup)
        val etPasswordSignup : EditText = findViewById(R.id.etPasswordSignup)
        val etConfirmSignup : EditText = findViewById(R.id.etConfirmSignup)
        val btnSignup : Button = findViewById(R.id.btnSignup)
        val tvToLogin : TextView = findViewById(R.id.tvToLogin)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        btnSignup.setOnClickListener {
            val email = etEmailSignup.text.toString()
            val password = etPasswordSignup.text.toString()
            val confirm = etConfirmSignup.text.toString()

            if (TextUtils.isEmpty(email)) {
                etEmailSignup.error = "Enter email here"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                etPasswordSignup.error = "Enter password here"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(confirm)) {
                etConfirmSignup.error = "Enter confirm password here"
                return@setOnClickListener
            }
            if (password != confirm) {
                etConfirmSignup.error = "Password doesn't match"
                return@setOnClickListener
            }
            if (password.length < 6) {
                etPasswordSignup.error = "Must be at least 6 characters"
                return@setOnClickListener
            }

            signup(email, password)
        }

        tvToLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

    }

    private fun signup(email : String, password : String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = Users(
                        uid = auth.currentUser?.uid.toString(),
                        email = email,
                        password = password
                    )

                    val uid = auth.currentUser?.uid.toString()

                    db.collection("Users").document(uid)
                        .set(user)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Your account has been registered.", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }

                } else {
                    Toast.makeText(this, "Sign up failed. Please try again.", Toast.LENGTH_LONG).show()
                }
            }
    }
}