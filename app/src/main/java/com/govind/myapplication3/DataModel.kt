package com.govind.myapplication3

data class DataModel(
    val vno: String,    // Assuming vehicle number is a String
    val timeE: Int,     // Assuming evening time is stored as an integer (military time)
    val timeM: Int      // Assuming morning time is stored as an integer (military time)
)
