package com.lp4.login.data.remote

import android.util.Log
import com.lp4.login.data.response.LoginResponse
import com.lp4.webservice.RetrofitNetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginDataSource {

    private val service = RetrofitNetworkClient
        .createNetworkClient()
        .create(LoginApi::class.java)

    suspend fun login(email: String, password: String): LoginResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val loginResponse = service.login(email = email, password = password)
                if (loginResponse.isSuccessful) {
                    loginResponse.body()
                } else {
                    null
                }
            } catch (ex: Exception) {
                Log.e("LoginDataSource", ex.message ?: "")
                null
            }
        }
    }
}