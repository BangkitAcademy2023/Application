package com.example.proton.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseUploadProduct(

	@field:SerializedName("data")
	val data: Data? = null
)

data class Data(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("namaProduk")
	val namaProduk: String? = null,

	@field:SerializedName("harga")
	val harga: Int? = null,

	@field:SerializedName("jumlahTerjual")
	val jumlahTerjual: Int? = null,

	@field:SerializedName("kodeProduk")
	val kodeProduk: String? = null,

	@field:SerializedName("expiredDate")
	val expiredDate: String? = null,

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("hargaJual")
	val hargaJual: Int? = null,

	@field:SerializedName("tipe")
	val tipe: String? = null,

	@field:SerializedName("jumlahProduk")
	val jumlahProduk: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
