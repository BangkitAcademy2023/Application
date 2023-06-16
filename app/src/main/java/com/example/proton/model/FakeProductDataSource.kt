package com.example.proton.model

import com.example.proton.R

object FakeProductDataSource {
    val dummyProduct = listOf(
        ProductModel(1, R.drawable.ori,"Basoka Original","A21", 112, "Frozen Food", "Siap saji","12/01/2024",20000, 30000, PredictionResult(11,15,12,25, "Hasil Prediksi")),
        ProductModel(2, R.drawable.urat,"Basoka Urat Jumbo","A22", 97, "Frozen Food", "Siap saji","15/01/2024",25000, 35000, PredictionResult(13,6,15,16,"Hasil Prediksi")),
        ProductModel(3, R.drawable.moza,"Basoka Mozarela","A23", 104, "Frozen Food", "Siap saji","12/01/2024",22000, 32000, PredictionResult(8,16,25,12,"Hasil Prediksi")),
    )
}