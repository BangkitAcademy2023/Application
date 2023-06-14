package com.example.proton.model

data class ProductRequestBody (
    val kodeProduk: String,
    val jumlahProduk: Int,
    val namaProduk: String,
    val kategori:String,
    val tipe: String,
    val harga: Int,
    val hargaJual: Int,
    val jumlahTerjual:Int,
    val expiredDate: String
)