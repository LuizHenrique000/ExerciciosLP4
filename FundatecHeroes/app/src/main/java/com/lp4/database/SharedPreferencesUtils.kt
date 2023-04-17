package com.lp4.database

import android.content.Context
import com.google.gson.Gson
import com.lp4.model.UsuarioResponse

object SharedPreferencesUtils {

    fun saveUser(context: Context, user: UsuarioResponse) {
        val sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val jsonUsuarioResponse = gson.toJson(user)
        editor.putString("user", jsonUsuarioResponse)
        editor.apply()
    }

    fun getUser(context: Context): UsuarioResponse? {
        val sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val gson = Gson()
        val jsonUsuarioResponse = sharedPreferences.getString("user", "")
        if (jsonUsuarioResponse.isNullOrEmpty()) {
            return null
        }
        return gson.fromJson(jsonUsuarioResponse, UsuarioResponse::class.java)
    }


}
