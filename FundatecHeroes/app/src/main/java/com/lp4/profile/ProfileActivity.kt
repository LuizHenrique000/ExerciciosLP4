
package com.lp4.profile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.lp4.R
import com.lp4.model.Usuario
import com.lp4.api.UsuarioClient
import com.lp4.databinding.ActivityProfileBinding
import com.lp4.login.view.LoginActivity
import kotlinx.coroutines.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.progressBar.visibility = View.GONE

        val scope = CoroutineScope(Dispatchers.IO)

        binding.buttomCadastrar.setOnClickListener {
            val nome = binding.nome.text.toString()
            val email = binding.email.text.toString()
            val senha = binding.senha.text.toString()
            val usuario = Usuario(nome, email, senha)

            scope.launch {
                val apiClient = UsuarioClient()
                val usuarioResponse = apiClient.createUser(usuario)
                withContext(Dispatchers.Main) {
                    binding.progressBar.visibility = View.VISIBLE
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