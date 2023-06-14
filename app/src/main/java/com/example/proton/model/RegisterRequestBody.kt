package com.example.proton.model

data class RegisterRequestBody (
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
    )