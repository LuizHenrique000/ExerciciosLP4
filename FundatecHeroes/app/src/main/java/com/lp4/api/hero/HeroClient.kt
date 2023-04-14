package com.lp4.api.hero

import com.lp4.model.Hero
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

class HeroClient {

    fun getUsers(): List<Hero> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fundatec.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        val call = service.getUsers()
        return call.execute().body() ?: emptyList()
    }

    fun createHero(): Hero {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fundatec.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        val call = service.createHero()
        return call.execute().body() ?: Hero("Teste", "Teste")
    }

    interface ApiService {
        @GET("/api/character/1")
        fun getUsers(): Call<List<Hero>>

        @POST("/api/character/1")
        fun createHero(): Call<Hero>

    }

}

