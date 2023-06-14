package com.example.proton.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proton.data.remote.Repository
import com.example.proton.di.Injection
import com.example.proton.ui.login.LoginViewModel
import com.example.proton.ui.management.ManagementViewModel
import com.example.proton.ui.managementDetailStore.DetailStoreViewModel
import com.example.proton.ui.product.ProductViewModel
import com.example.proton.ui.register.RegisterViewModel
import com.example.proton.ui.recommendation.RecommendationViewModel

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ManagementViewModel::class.java)) {
            return ManagementViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailStoreViewModel::class.java)) {
            return DetailStoreViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(RecommendationViewModel::class.java)) {
            return RecommendationViewModel(repository) as T
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