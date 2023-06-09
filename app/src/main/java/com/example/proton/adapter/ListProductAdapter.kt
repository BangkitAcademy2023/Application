package com.example.proton.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proton.R
import com.example.proton.model.ProductModel
import de.hdodenhof.circleimageview.CircleImageView

class ListProductAdapter(private val listData: List<ProductModel>) : RecyclerView.Adapter<ListProductAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.photo_product)
        val name: TextView = itemView.findViewById(R.id.productName)
        val category: TextView = itemView.findViewById(R.id.productCategory)
        val stock : TextView = itemView.findViewById(R.id.productStock)
        val type : TextView = itemView.findViewById(R.id.productType)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup, false)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}