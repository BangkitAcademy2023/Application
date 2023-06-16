package com.example.proton.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.proton.data.remote.Repository
import com.example.proton.data.remote.response.ResponseUploadProduct
import com.example.proton.model.ProductRequestBody
import com.example.proton.data.remote.Result

class ProductViewModel(private val repository: Repository): ViewModel() {
    lateinit var productPost: LiveData<Result<ResponseUploadProduct>>

    fun postProduct(data: ProductRequestBody) {
        productPost = repository.postProduct(data)
    }
}