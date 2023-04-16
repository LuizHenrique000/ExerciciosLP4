package com.lp4.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

class UsuarioClient {

    fun getUser(email: String, password: String): UsuarioResponse? {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fundatec.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(UserService::class.java)
        val call = service.getUser(email, password)
        return call.execute().body()
    }

    fun createUser(user: Usuario): UsuarioResponse? {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fundatec.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(UserService::class.java)
        val call = service.createUser(user)
        return call.execute().body()
    }

    interface UserService {
        @GET("api/login")
        fun getUser(
            @Query("email") email: String,
            @Query("password") password: String
        ): Call<UsuarioResponse>

        @POST("api/login")
        fun createUser(
            @Body usuario: Usuario
        ): Call<UsuarioResponse>

    }

}