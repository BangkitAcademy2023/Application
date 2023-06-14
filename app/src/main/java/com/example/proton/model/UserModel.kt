package com.example.proton.model

data class UserModel(
    val email: String,
    val password: String,
    val isLogin: Boolean,
    val token: String
)