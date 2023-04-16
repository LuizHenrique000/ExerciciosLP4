
package com.lp4.profile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.lp4.api.Usuario
import com.lp4.api.UsuarioClient
import com.lp4.databinding.ActivityProfileBinding
import com.lp4.login.view.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val scope = CoroutineScope(Dispatchers.IO)

        binding.buttomCadastrar.setOnClickListener {
            val nome = binding.nome.text.toString()
            val email = binding.email.text.toString()
            val senha = binding.senha.text.toString()

            val usuario = Usuario(nome, email, senha)

            scope.launch {
                val apiClient = UsuarioClient()
                val usuarioResponse = apiClient.createUser(usuario)
                println(usuarioResponse)
                withContext(Dispatchers.Main) {
                    val sharedPreferences = getSharedPreferences("user_response", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    val gson = Gson()
                    val jsonUsuarioResponse = gson.toJson(usuarioResponse)
                    editor.putString("user_response", jsonUsuarioResponse)
                    editor.apply()
                    val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
            }

        }

    }
}