package com.govind.myapplication3

import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity2 : AppCompatActivity() {

    private lateinit var adapter: MyAdapter
    private val schedList = arrayListOf<Sched>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        // Reference to Firebase database
        val database = FirebaseDatabase.getInstance()
        val schedRef = database.getReference("Sched")
        var count = 0

        val recyclerView = findViewById<RecyclerView>(R.id.schedList)
        adapter = MyAdapter(schedList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        schedRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val schedule = snapshot.getValue(Sched::class.java)
                    if (schedule != null) {
                        // You can now use the schedule object
                        count++
                        val timeM = schedule.timeM
                        Log.d("data $count", "TimeM: $timeM")
                        count++
                        val timeN = schedule.timeN
                        Log.d("data $count", "TimeN: $timeN")
                        schedList.add(schedule)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle possible errors.
                println("Database error: ${databaseError.message}")
            }
        })





    }
}