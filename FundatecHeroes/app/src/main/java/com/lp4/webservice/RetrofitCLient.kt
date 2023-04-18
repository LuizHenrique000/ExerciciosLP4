package com.lp4.webservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCLient {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://fundatec.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}