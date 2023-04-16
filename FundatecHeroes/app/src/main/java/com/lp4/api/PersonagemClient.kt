package com.lp4.api

import com.lp4.model.Personagem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

class PersonagemClient {

//   fun createPersonagens(id: String, personagem: Personagem): List<Personagem>? {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://fundatec.herokuapp.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val service = retrofit.create(PersonagemService::class.java)
//        val call = service.createPersonagem(id, personagem)
//        return call.execute().body()
//    }

    fun getPersonagens(id: String): List<Personagem>? {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fundatec.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PersonagemService::class.java)
        val call = service.getPersonagens(id)

        return call.execute().body()
    }

    interface PersonagemService {
        @GET("/api/character/")
        fun getPersonagens(
            @Query("id") id: String): Call<List<Personagem>>

        @POST("/api/character/")
        fun createPersonagem(
            @Query("id") id: String, personagem: Personagem): Call<Personagem>

    }
}