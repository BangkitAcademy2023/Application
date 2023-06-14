package com.example.proton.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.proton.data.remote.Repository
import com.example.proton.data.remote.response.ResponseRegister
import com.example.proton.model.RegisterRequestBody
import com.example.proton.data.remote.Result

class RegisterViewModel(private val repository: Repository): ViewModel() {
    lateinit var registerPost: LiveData<Result<ResponseRegister>>

    fun postRegister(data: RegisterRequestBody) {
        registerPost = repository.postUserRegister(data)
    }
}