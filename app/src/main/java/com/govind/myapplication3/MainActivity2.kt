package com.govind.myapplication3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class MainActivity2 : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var adapter: MyAdapter
    private lateinit var dataList: MutableList<Sched>
    private lateinit var notificationHelper: NotificationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        recyclerView = findViewById(R.id.schedList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        dataList = mutableListOf()
        adapter = MyAdapter(dataList)
        recyclerView.adapter = adapter

        notificationHelper = NotificationHelper(this) // Ensure 'this' is the correct context

        databaseReference = FirebaseDatabase.getInstance().getReference("Sched")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (dataSnapshot in snapshot.children) {
                    val sched = dataSnapshot.getValue(Sched::class.java)
                    sched?.let {
                        dataList.add(it)
                        scheduleNotification(it)
                    }
                }
                adapter.notifyDataSetChanged()
                Log.d("MainActivity2", "Data loaded: ${dataList.size} items")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainActivity2", "Failed to read value.", error.toException())
            }
        })
    }

    private fun scheduleNotification(sched: Sched) {
        // Ensure sched.timeE is valid and not null
        if (sched.timeE > 0 && !sched.vnO.isNullOrEmpty()) {
            // Convert military time to AM/PM format
            val notificationTimeFormatted = convertMilitaryTimeToAMPM(sched.timeE)

            // Construct the notification message
            val title = "Reminder"
            val message = "Your garbage collector will arrive at ${notificationTimeFormatted}"

            // Schedule the notification
            notificationHelper.sendNotification(title, message)
        } else {
            Log.e("MainActivity2", "Invalid schedule data: $sched")
        }
    }

    // Function to convert military time to AM/PM format
    private fun convertMilitaryTimeToAMPM(militaryTime: Long): String {
        val hours = (militaryTime / 100).toInt()
        val minutes = (militaryTime % 100).toInt()
        val period = if (hours >= 12) "PM" else "AM"
        val hours12 = if (hours == 0 || hours == 12) 12 else hours % 12
        return String.format("%02d:%02d %s", hours12, minutes, period)
    }
}
