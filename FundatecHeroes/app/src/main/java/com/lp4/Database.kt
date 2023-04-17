//package com.lp4
//
//import android.content.Context
//import com.google.gson.Gson
//import com.lp4.character.view.NewCharacterActivity
//import com.lp4.model.UsuarioResponse
//
//class Database(newCharacterActivity: NewCharacterActivity) {
//
//    fun saveUserInternal(user: UsuarioResponse?) {
//        val sharedPreferences = getSharedPreferences("user_response", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        val gson = Gson()
//        val jsonUsuarioResponse = gson.toJson(user)
//        editor.putString("user_response", jsonUsuarioResponse)
//        editor.apply()
//    }
//
//    fun getUserInternal(context: Context): UsuarioResponse? {
//        val sharedPreferences = context.getSharedPreferences("user_response", Context.MODE_PRIVATE)
//        val gson = Gson()
//        val jsonUsuarioResponse = sharedPreferences.getString("user_response", "")
//        return gson.fromJson(jsonUsuarioResponse, UsuarioResponse::class.java)
//    }
//
//}