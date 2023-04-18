package com.lp4.api

import com.lp4.model.User
import com.lp4.webservice.RetrofitCLient
import retrofit2.Call
import retrofit2.http.*

class CharacterClient {

    fun createCharacter(id: String, user: User): User? {
      return RetrofitCLient.getRetrofit()
            .create(CharacterService::class.java)
            .createCharacter(id, user)
            .execute()
            .body()
    }

    fun getCharacter(id: String): List<User>? {
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
        ): Call<List<User>>

        @POST("api/character/{id}")
        fun createCharacter(
            @Path("id") id: String,
            @Body user: User
        ): Call<User>

    }
}