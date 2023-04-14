package com.lp4.api.user

import com.lp4.model.User
import com.lp4.model.UserRequest
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

class UserClient {

    fun getUser(email: String, password: String): User? {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fundatec.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(UserClient.UserService::class.java)
        val call = service.getUser(email, password)
        return call.execute().body()
    }

    fun createUser(user: UserRequest): User? {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fundatec.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(UserClient.UserService::class.java)
        val call = service.createUser(user)
        return call.execute().body()
    }

    interface UserService {

        @POST("/api/login")
        fun createUser(@Body user: UserRequest): Call<User>

        @GET("/api/login")
        fun getUser(@Query("email") email: String, @Query("password") password: String): Call<User>

    }
}