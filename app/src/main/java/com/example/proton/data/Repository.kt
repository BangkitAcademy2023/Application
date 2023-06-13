package com.example.proton.data

import com.example.proton.model.FakeProductDataSource
import com.example.proton.model.FakeStoreDataSource
import com.example.proton.model.ProductModel
import com.example.proton.model.StoreModel

class Repository private constructor() {

    private val dataProduct = FakeProductDataSource.dummyProduct.toMutableList()
    private val dataStore = FakeStoreDataSource.dummyStore.toMutableList()

    fun getProduct(): List<ProductModel> {
        return dataProduct
    }

    fun getStore(): List<StoreModel> {
        return dataStore
    }

    fun searchProduct(query: String): List<ProductModel> {
        return dataProduct.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun searchStore(query: String): List<StoreModel> {
        return dataStore.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository().also { instance = it }
            }
    }
}