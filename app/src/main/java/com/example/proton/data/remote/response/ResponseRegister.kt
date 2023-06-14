package com.example.proton.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseRegister(

	@field:SerializedName("payload")
	val payload: PayloadRegister? = null
)

data class PayloadRegister(

	@field:SerializedName("users")
	val users: String? = null
)
