package com.govind.myapplication3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var recyclerbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerbtn = findViewById(R.id.recyclerviewbtn)

        recyclerbtn.setOnClickListener {

            var i = Intent(this,MainActivity2::class.java)
            startActivity(i)


    }}
}