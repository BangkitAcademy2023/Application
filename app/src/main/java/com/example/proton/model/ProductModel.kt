package com.example.proton.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductModel(
    val id: Long,
    val image: Int,
    val name: String,
    val code: String,
    val stock: Int,
    val category: String,
    val type: String,
    val dateExp: String,
    val price: Int,
    val sellingPrice: Int
): Parcelable