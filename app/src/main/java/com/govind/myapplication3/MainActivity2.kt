package com.govind.myapplication3

import android.os.Bundle
import android.util.Log
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

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var schedArryaList: ArrayList<Sched>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        userRecyclerView = findViewById(R.id.userList)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        schedArryaList= arrayListOf<Sched>()
        getSchedData()




    }

    private fun getSchedData() {
        dbref = FirebaseDatabase.getInstance().reference

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){

                        val Sched = userSnapshot.getValue(Sched::class.java)
                        schedArryaList.add(Sched!!)
                        Log.d("vinit", "onDataChange: ${schedArryaList}")

                    }
                    userRecyclerView.adapter= MyAdapter(schedArryaList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}