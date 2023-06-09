package com.example.proton.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proton.MainDataSource
import com.example.proton.model.UserPreferences
import com.example.proton.ui.login.LoginDataSource

class DataSourceManager(private val pref: UserPreferences) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainDataSource::class.java) -> {
                MainDataSource(pref) as T
            }
            modelClass.isAssignableFrom(LoginDataSource::class.java) -> {
                LoginDataSource(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }


}