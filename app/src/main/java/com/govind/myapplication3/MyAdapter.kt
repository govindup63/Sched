package com.govind.myapplication3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val userlist : ArrayList<Sched>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        return  MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userlist[position]
        holder.timeM.text = currentitem.timeM.toString()
        holder.timeN.text = currentitem.timeN.toString()
        holder.vehicleNo.text = currentitem.vnO.toString()
    }
    class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val timeM : TextView = itemView.findViewById(R.id.tvMtime)
        val timeN : TextView = itemView.findViewById(R.id.tvNtime)
        val vehicleNo: TextView = itemView.findViewById(R.id.Vno)
    }
}