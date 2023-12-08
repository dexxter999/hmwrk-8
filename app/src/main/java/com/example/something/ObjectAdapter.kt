package com.example.something

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ObjectAdapter : ListAdapter<Object, ObjectAdapter.ObjectViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.object_item_layout, parent, false)
        return ObjectViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class ObjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(currentItem: Object) {
            val itemNameTextView: TextView = itemView.findViewById(R.id.object_name_text)
            itemNameTextView.text = currentItem.name
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Object>() {
        override fun areItemsTheSame(oldItem: Object, newItem: Object): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Object, newItem: Object): Boolean {
            return oldItem == newItem
        }
    }
}

