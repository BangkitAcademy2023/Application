package com.example.proton.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proton.R
import com.example.proton.model.ProductModel
import com.example.proton.ui.managementDetailProduct.ManagementDetailProductActivity

class ListProductAdapter(private val listData: List<ProductModel>) : RecyclerView.Adapter<ListProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.item_product, viewGroup, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]

        holder.name.text = data.name
        holder.category.text = data.category
        holder.photo.setImageResource(data.image)
        holder.stock.text = data.stock.toString()
        holder.type.text = data.type

        holder.itemView.setOnClickListener{
            val intentDetail = Intent(holder.itemView.context, ManagementDetailProductActivity::class.java)
            intentDetail.putExtra(ManagementDetailProductActivity.DATA_PRODUCT, data)
            intentDetail.putExtra(ManagementDetailProductActivity.NAME_PRODUCT, data.name)
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount() = listData.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.photoProduct)
        val name: TextView = itemView.findViewById(R.id.productName)
        val category: TextView = itemView.findViewById(R.id.productCategory)
        val stock : TextView = itemView.findViewById(R.id.productStock)
        val type : TextView = itemView.findViewById(R.id.productType)
    }
}