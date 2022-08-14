package com.example.todoapp

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view.view.*

class Adapter(var carddetails : List<cardInfo>): RecyclerView.Adapter<Adapter.viewholder>() {

    class viewholder(view: View) : RecyclerView.ViewHolder(view) {

        var tittle = view.tittle
        var priority = view.priority
        var background = view.layout_background!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        var itemview = LayoutInflater.from(parent.context).inflate(R.layout.view,parent,false)

        return viewholder(itemview)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        when (carddetails[position].priority!!.toLowerCase())
        {
            "high"-> holder.background.setBackgroundColor(Color.parseColor("#D61616"))
            "low" -> holder.background.setBackgroundColor(Color.parseColor("#D65316"))
            "medium" -> holder.background.setBackgroundColor(Color.parseColor("#E3F30F"))
            else-> holder.background.setBackgroundColor(Color.parseColor("#6F6F6F"))
        }

        holder.tittle.text = carddetails[position].tittle
        holder.priority.text = carddetails[position].priority

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,updateActivity::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return carddetails.size
    }

}