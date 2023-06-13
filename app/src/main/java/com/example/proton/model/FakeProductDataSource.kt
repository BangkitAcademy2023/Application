package com.example.proton.model

import com.example.proton.R

object FakeProductDataSource {
    val dummyProduct = listOf(
        ProductModel(1, R.drawable.ori,"Basoka Original","A21", 112, "Frozen Food", "Siap saji","12/01/2024",20000, 30000, FakeStoreDataSource.dummyStore),
        ProductModel(2, R.drawable.urat,"Basoka Urat Jumbo","A22", 97, "Frozen Food", "Siap saji","15/01/2024",25000, 35000, FakeStoreDataSource.dummyStore),
        ProductModel(3, R.drawable.moza,"Basoka Mozarela","A23", 104, "Frozen Food", "Siap saji","12/01/2024",22000, 32000, FakeStoreDataSource.dummyStore),
        )
}