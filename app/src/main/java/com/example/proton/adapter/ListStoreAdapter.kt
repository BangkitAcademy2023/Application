package com.example.proton.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proton.R
import com.example.proton.model.ProductModel
import com.example.proton.model.StoreModel

class ListStoreAdapter(private val listData: List<StoreModel>) : RecyclerView.Adapter<ListStoreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.item_store, viewGroup, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]

        holder.name.text = data.name
        holder.photo.setImageResource(data.image)
        holder.address.text = data.address
        holder.number.text = data.noHp

    }

    override fun getItemCount() = listData.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.photoStore)
        val name: TextView = itemView.findViewById(R.id.storeName)
        val address : TextView = itemView.findViewById(R.id.storeAddress)
        val number : TextView = itemView.findViewById(R.id.storeNumber)
    }
}