package com.example.proton.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.proton.model.FakeProductDataSource
import com.example.proton.model.FakeStoreDataSource
import com.example.proton.model.ProductModel
import com.example.proton.model.StoreModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.proton.data.remote.Result
import com.example.proton.data.remote.response.ResponseLogin
import com.example.proton.data.remote.response.ResponsePredict
import com.example.proton.data.remote.response.ResponseProduct
import com.example.proton.data.remote.response.ResponseRegister
import com.example.proton.data.remote.response.ResponseUploadProduct
import com.example.proton.data.remote.retrofit.ApiService
import com.example.proton.model.LoginRequestBody
import com.example.proton.model.ProductRequestBody
import com.example.proton.model.RegisterRequestBody
import okhttp3.MultipartBody
import okhttp3.RequestBody

class Repository(
    private val apiService: ApiService,
) {

    private fun <T> makeApiCall(apiCall: Call<T>): LiveData<Result<T>> {
        val result = MutableLiveData<Result<T>>()

        result.value = Result.Loading
        apiCall.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    val items = response.body()
                    if (items != null) {
                        result.value = Result.Success(items)
                    } else {
                        result.value = Result.Error("Data not found")
                    }
                } else {
                    result.value = Result.Error("Error ${response.code()}")
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                result.value = Result.Error(t.message ?: "Unknown error")
            }
        })

        return result
    }

    fun postUserLogin(data: LoginRequestBody) : LiveData<Result<ResponseLogin>> {
        return makeApiCall(apiService.login(data))
    }

    fun postUserRegister(data: RegisterRequestBody) : LiveData<Result<ResponseRegister>>{
        return makeApiCall(apiService.registerUser(data))
    }

    fun getAllProduct(): LiveData<Result<ResponseProduct>>{
        return makeApiCall(apiService.getAllProduct())
    }

    fun postProduct(data: ProductRequestBody): LiveData<Result<ResponseUploadProduct>>{
        return makeApiCall(apiService.postProduct(data))
    }

    fun getSearchProduct(name: String): LiveData<Result<ResponseProduct>>{
        return makeApiCall(apiService.getProduct(name))
    }







    private val dataProduct = FakeProductDataSource.dummyProduct.toMutableList()
    private val dataStore = FakeStoreDataSource.dummyStore.toMutableList()

    fun getProduct(): List<ProductModel> {
        return dataProduct
    }

    fun getStore(): List<StoreModel> {
        return dataStore
    }

    fun searchProduct(query: String): List<ProductModel> {
        return dataProduct.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun searchStore(query: String): List<StoreModel> {
        return dataStore.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            apiService: ApiService,
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(apiService).also { instance = it }
            }
    }
}