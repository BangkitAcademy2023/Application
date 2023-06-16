package com.example.proton.data.remote.retrofit

import com.example.proton.data.remote.response.*
import com.example.proton.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.*

interface ApiService {


    @POST("regist")
    fun registerUser(
        @Body requestBody: RegisterRequestBody
    ): Call<ResponseRegister>

    @POST("login")
    fun login(
        @Body
        userData:LoginRequestBody
    ):Call<ResponseLogin>

    @GET("product")
    fun getAllProduct(): Call<ResponseProduct>

    @POST("product")
    fun postProduct(
        @Body requestBody: ProductRequestBody
    ): Call<ResponseUploadProduct>

    @GET("product")
    fun getProduct(@Query("name") name: String): Call<ResponseProduct>

}