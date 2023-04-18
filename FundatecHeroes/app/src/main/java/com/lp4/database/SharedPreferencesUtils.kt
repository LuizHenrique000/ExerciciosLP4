package com.lp4.database

import android.content.Context
import com.google.gson.Gson
import com.lp4.model.UsuarioResponse

class SharedPreferencesUtils {

    fun saveUser(context: Context, user: UsuarioResponse) {
        val sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("id", user.id)
        editor.apply()
    }

    fun getUser(context: Context): Int {
        val sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val id = sharedPreferences.getInt("id", 0)
        return id;
    }


}
