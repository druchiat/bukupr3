package com.example.bukupr3

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bukupr3.model.BukuPr
import com.google.firebase.ktx.Firebase

class AdapterBukuPr () : RecyclerView.Adapter <AdapterBukuPr.ViewHolder>(){

    var bukuPr : ArrayList<BukuPr> = ArrayList()

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        var tvItemNamaBukuPr : TextView = view.findViewById(R.id.tvItemNamaBukuPr)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bukupr,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItemNamaBukuPr.text = bukuPr[position].namaBukuPr

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, TugasActivity::class.java)
            intent.putExtra("bukupr", bukuPr[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return bukuPr.size
    }

}