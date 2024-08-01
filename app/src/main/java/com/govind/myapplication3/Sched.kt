package com.govind.myapplication3

data class Sched(
    val vnO: String = "", // Example default value, adjust as per your requirements
    val timeE: Long = 0,
    val timeM: Long = 0
) {
    constructor() : this("", 0, 0)
}

