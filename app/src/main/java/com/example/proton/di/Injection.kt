package com.example.proton.di

import com.example.proton.data.Repository

object Injection {

    fun provideRepository(): Repository {
//        val apiService = ApiConfig.getApiService()
        return Repository.getInstance()
    }
}