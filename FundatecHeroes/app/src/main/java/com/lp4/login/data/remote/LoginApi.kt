package com.lp4.login.data.remote

import retrofit2.http.Query
import com.lp4.login.data.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginApi {

    @GET("api/login")
    suspend fun login(

        @Query("email")
        email: String,

        @Query("password")
        password: String
    ): Response<LoginResponse>
}