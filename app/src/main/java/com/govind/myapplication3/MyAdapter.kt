package com.govind.myapplication3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val dataList: List<Sched>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val vnoTextView: TextView = itemView.findViewById(R.id.Vno)
        private val tvNtimeTextView: TextView = itemView.findViewById(R.id.tvNtime)
        private val tvMtimeTextView: TextView = itemView.findViewById(R.id.tvMtime)

        fun bind(sched: Sched) {
            vnoTextView.text = sched.vnO
            tvNtimeTextView.text = ": ${convertMilitaryTimeToAMPM(sched.timeE)}"
            tvMtimeTextView.text = ": ${convertMilitaryTimeToAMPM(sched.timeM)}"
        }

        private fun convertMilitaryTimeToAMPM(militaryTime: Long): String {
            val hours = (militaryTime / 100).toInt()
            val minutes = (militaryTime % 100).toInt()
            val period = if (hours >= 12) "PM" else "AM"
            val hours12 = if (hours == 0 || hours == 12) 12 else hours % 12
            return String.format("%02d:%02d %s", hours12, minutes, period)
        }
    }
}
