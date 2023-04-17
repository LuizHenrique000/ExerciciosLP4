package com.lp4.api

import com.lp4.BuildConfig
import com.lp4.model.Personagem
import com.lp4.webservice.RetrofitCLient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class PersonagemClient {

    fun createPersonagem(id: String, personagem: Personagem): Personagem? {
      return RetrofitCLient.getRetrofit()
            .create(PersonagemService::class.java)
            .createPersonagem(id, personagem)
            .execute()
            .body()
    }

    fun getPersonagens(id: String): List<Personagem>? {
        return RetrofitCLient.getRetrofit()
            .create(PersonagemService::class.java)
            .getPersonagens(id)
            .execute()
            .body()
    }

    interface PersonagemService {

        @GET("api/character/{id}")
        fun getPersonagens(
            @Path("id") id: String,
        ): Call<List<Personagem>>

        @POST("api/character/{id}")
        fun createPersonagem(
            @Path("id") id: String,
            @Body personagem: Personagem
        ): Call<Personagem>

    }
}