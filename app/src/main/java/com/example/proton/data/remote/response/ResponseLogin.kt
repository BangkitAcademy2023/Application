package com.example.proton.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("payload")
	val payload: PayloadLogin? = null
)

data class PayloadLogin(

	@field:SerializedName("token")
	val token: String? = null
)
