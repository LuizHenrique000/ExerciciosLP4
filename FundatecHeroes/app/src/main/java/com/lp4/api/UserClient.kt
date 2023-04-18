package com.lp4.api

import com.lp4.model.Usuario
import com.lp4.model.UsuarioResponse
import com.lp4.webservice.RetrofitCLient
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

class UserClient {

    fun getUser(email: String, password: String): UsuarioResponse? {
        return RetrofitCLient.getRetrofit()
            .create(UserService::class.java)
            .getUser(email, password)
            .execute()
            .body()
    }

    fun createUser(user: Usuario): UsuarioResponse? {
        return RetrofitCLient.getRetrofit()
            .create(UserService::class.java)
            .createUser(user)
            .execute()
            .body()
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