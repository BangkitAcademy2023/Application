package com.example.proton.data.remote.retrofit

import com.example.proton.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object {
        fun getApiService(): ApiService {
            val loggingInterceptor = if(BuildConfig.DEBUG) { HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY) }else { HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.NONE) }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://proton-387902.et.r.appspot.com/api/v1/cms/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}