package com.lp4.api

import com.lp4.model.Character
import com.lp4.webservice.RetrofitCLient
import retrofit2.Call
import retrofit2.http.*

class CharacterClient {

    fun createCharacter(id: String, character: Character): Character? {
      return RetrofitCLient.getRetrofit()
            .create(CharacterService::class.java)
            .createCharacter(id, character)
            .execute()
            .body()
    }

    fun getCharacter(id: String): List<Character>? {
        return RetrofitCLient.getRetrofit()
            .create(CharacterService::class.java)
            .getcharacter(id)
            .execute()
            .body()
    }

    interface CharacterService {

        @GET("api/character/{id}")
        fun getcharacter(
            @Path("id") id: String,
        ): Call<List<Character>>

        @POST("api/character/{id}")
        fun createCharacter(
            @Path("id") id: String,
            @Body character: Character
        ): Call<Character>

    }
}