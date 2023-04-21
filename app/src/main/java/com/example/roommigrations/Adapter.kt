package com.example.roommigrations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roommigrations.databinding.ListViewItemBinding
import com.example.roommigrations.models.Employee

class Adapter(val list :List<Employee>) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(val binding : ListViewItemBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ListViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val employee = list[position]
        if (holder is ViewHolder) {
            holder.binding.employee = employee
            holder.binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}