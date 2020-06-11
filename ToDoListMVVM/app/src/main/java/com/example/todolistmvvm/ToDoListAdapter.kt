package com.example.todolistmvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter(context: Context, arrayListDetails:ArrayList<Model>) : RecyclerView.Adapter<ToDoListAdapter.ListHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val itemList: ArrayList<Model> = arrayListDetails

    class ListHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)

    }

    fun addItems(items: ArrayList<Model>) {
        val size = itemList.size
        itemList.addAll(items)
        notifyItemRangeInserted(size, items.size)
    }

    fun remove(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val v = layoutInflater.inflate(R.layout.adapter_layout, parent, false)
        return ListHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.tvTitle.text = itemList[position].title
        holder.tvStatus.text = "Completed : " + itemList[position].completed
        holder.tvId.text = itemList[position].id
    }

}