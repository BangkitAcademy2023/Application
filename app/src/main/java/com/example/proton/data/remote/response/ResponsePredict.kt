package com.example.proton.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponsePredict(

	@field:SerializedName("data")
	val data: DataPredict? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataPredict(

	@field:SerializedName("w1")
	val w1: Int? = null,

	@field:SerializedName("w2")
	val w2: Int? = null,

	@field:SerializedName("w3")
	val w3: Int? = null,

	@field:SerializedName("w4")
	val w4: Int? = null
)
