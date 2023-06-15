package com.example.proton.di

import com.example.proton.data.remote.Repository
import com.example.proton.data.remote.retrofit.ApiConfig

object Injection {

    fun provideRepository(): Repository {
        val apiService = ApiConfig.getApiService()

        return Repository.getInstance(apiService)
    }
}