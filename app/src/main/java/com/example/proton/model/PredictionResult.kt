package com.example.proton.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PredictionResult(
    val w1: Int,
    val w2: Int,
    val w3: Int,
    val w4: Int,
    val message: String
): Parcelable