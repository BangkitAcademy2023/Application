package com.example.proton.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.proton.data.remote.Repository
import com.example.proton.data.remote.response.ResponseLogin
import com.example.proton.model.LoginRequestBody
import com.example.proton.data.remote.Result


class LoginViewModel(private val repository: Repository) : ViewModel() {
    lateinit var loginPost: LiveData<Result<ResponseLogin>>

    fun postLogin(data: LoginRequestBody) {
        loginPost = repository.postUserLogin(data)
    }
}