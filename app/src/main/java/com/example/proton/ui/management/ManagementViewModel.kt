package com.example.proton.ui.management

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.example.proton.data.Repository
import com.example.proton.model.ProductModel
import com.example.proton.model.StoreModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ManagementViewModel(private val repository: Repository): ViewModel() {

    private val _groupedProduct = MutableStateFlow(
        repository.getProduct()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    private val _groupedStore = MutableStateFlow(
        repository.getStore()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )

    val groupedProduct: StateFlow<Map<Char, List<ProductModel>>> get() = _groupedProduct
    val groupedStore: StateFlow<Map<Char, List<StoreModel>>> get() = _groupedStore

    private val _query = MutableStateFlow("")
    fun searchProduct(newQuery: String) {
        _query.value = newQuery
        _groupedProduct.value = repository.searchProduct(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }

    fun searchStore(newQuery: String) {
        _query.value = newQuery
        _groupedStore.value = repository.searchStore(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}