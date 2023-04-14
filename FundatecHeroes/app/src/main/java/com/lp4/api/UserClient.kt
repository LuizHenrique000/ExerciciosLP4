package com.lp4.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class UserClient {

    fun getUsers(): List<User> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gorest.co.in/public/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GitHubService::class.java)
        val call = service.getUsers()

        return call.execute().body() ?: emptyList()
    }

    interface GitHubService {
        @GET("users")
        fun getUsers(): Call<List<User>>
    }

}

