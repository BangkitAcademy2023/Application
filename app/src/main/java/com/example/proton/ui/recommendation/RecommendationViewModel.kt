package com.example.proton.ui.recommendation

import androidx.lifecycle.ViewModel
import com.example.proton.data.remote.Repository
import com.example.proton.model.StoreModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecommendationViewModel(private val repository: Repository): ViewModel() {
    private val _groupedStore = MutableStateFlow(
        repository.getStore()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )

    val groupedStore: StateFlow<Map<Char, List<StoreModel>>> get() = _groupedStore

    private val _query = MutableStateFlow("")


    fun searchStore(newQuery: String) {
        _query.value = newQuery
        _groupedStore.value = repository.searchStore(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}