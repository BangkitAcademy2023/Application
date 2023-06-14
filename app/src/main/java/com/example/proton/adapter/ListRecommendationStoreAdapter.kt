package com.example.proton.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proton.R
import com.example.proton.model.StoreModel
import com.example.proton.ui.managementDetailStore.ManagementDetailStoreActivity
import com.example.proton.ui.store.StoreActivity

class ListRecommendationStoreAdapter(private val listData: List<StoreModel>) : RecyclerView.Adapter<ListRecommendationStoreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.item_store, viewGroup, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]

        holder.name.text = data.name
        holder.photo.setImageResource(data.image)
        holder.address.text = data.address
        holder.number.text = data.noHp

        holder.itemView.setOnClickListener{
            val intentDetail = Intent(holder.itemView.context, StoreActivity::class.java)
            intentDetail.putExtra(StoreActivity.DATA_STORE, data)
            holder.itemView.context.startActivity(intentDetail)
        }

    }

    override fun getItemCount() = listData.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.photoStore)
        val name: TextView = itemView.findViewById(R.id.storeName)
        val address : TextView = itemView.findViewById(R.id.storeAddress)
        val number : TextView = itemView.findViewById(R.id.storeNumber)
    }
}