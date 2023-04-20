package com.lp4.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lp4.model.Usuario
import com.lp4.api.UserClient
import com.lp4.database.SharedPreferencesUtils
import com.lp4.databinding.ActivityProfileBinding
import com.lp4.login.view.LoginActivity
import kotlinx.coroutines.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val sharedPreferences: SharedPreferencesUtils by lazy {
        SharedPreferencesUtils()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.progressBar.visibility = View.GONE

        binding.buttomCadastrar.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            val nome = binding.nome.text.toString()
            val email = binding.email.text.toString()
            val senha = binding.senha.text.toString()
            val usuario = Usuario(nome, email, senha)

            CoroutineScope(Dispatchers.IO).launch {
                val apiClient = UserClient()
                val usuarioResponse = apiClient.createUser(usuario)
                withContext(Dispatchers.Main) {
                    sharedPreferences.saveUser(this@ProfileActivity, usuarioResponse!!)
                    binding.progressBar.visibility = View.GONE
                    startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
                }
            }

        }
    }
}
