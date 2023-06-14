package com.example.proton.ui.managementDetailStore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.proton.data.remote.Repository
import com.example.proton.data.remote.Result
import com.example.proton.data.remote.response.ResponseProduct

class DetailStoreViewModel(private val repository: Repository): ViewModel() {
    lateinit var listProduct: LiveData<Result<ResponseProduct>>

    fun getAllProduct() {
        listProduct = repository.getAllProduct()
    }
}