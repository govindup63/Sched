package com.govind.myapplication3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp // Import FirebaseApp

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Call super.onCreate() first
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this) // Initialize Firebase

        recyclerbtn = findViewById(R.id.recyclerviewbtn)

        recyclerbtn.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}
