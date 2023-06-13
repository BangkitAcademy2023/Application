package com.example.proton.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proton.data.Repository
import com.example.proton.di.Injection
import com.example.proton.ui.login.LoginViewModel
import com.example.proton.ui.management.ManagementViewModel
import com.example.proton.ui.register.RegisteViewModel

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(RegisteViewModel::class.java)) {
            return RegisteViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ManagementViewModel::class.java)) {
            return ManagementViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }.also { instance = it }
    }
}