package com.example.proton.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoreModel(
    val id: Long,
    val image: Int,
    val name: String,
    val address: String,
    val noHp: String
): Parcelable