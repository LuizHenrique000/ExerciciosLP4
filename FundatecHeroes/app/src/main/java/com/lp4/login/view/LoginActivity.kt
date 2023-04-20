package com.lp4.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.lp4.api.UserClient
import com.lp4.database.SharedPreferencesUtils
import com.lp4.home.HomeActivity
import com.lp4.profile.ProfileActivity
import com.lp4.databinding.LoginActivityBinding
import com.lp4.login.presentation.LoginViewModel
import com.lp4.login.presentation.ViewState
import com.lp4.model.Usuario
import kotlinx.coroutines.*

open class LoginActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Dispatchers.IO)
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginActivityBinding
    private val sharedPreferences: SharedPreferencesUtils by lazy {
        SharedPreferencesUtils()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.progressBar.visibility = View.GONE

        binding.novoPorAqui.setOnClickListener() {
            irParaATelaDeCadastro()
        }

        configLoginButton()
        viewModel.viewState.observe(this) { state ->
            when (state) {
                is
                ViewState.ShowError -> mostrarErro()
                ViewState.ShowErrorEmail -> {}
                ViewState.ShowErrorPassword -> {}
                ViewState.ShowSuccess -> {}
                else -> {}
            }
        }
    }

    private fun configLoginButton() {
        binding.buttomLogin.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.validarInputs(
                email = binding.email.text.toString(),
                senha = binding.senha.text.toString()
            )
            if (viewModel.viewState.value == ViewState.ShowSuccess) {
                salvarUsuario()
            }
        }
    }

    private fun salvarUsuario() {
        val email = binding.email.text.toString()
        val senha = binding.senha.text.toString()
        scope.launch {
            val apiClient = UserClient()
            val usuarioResponse = apiClient.getUser(email, senha)
            if (usuarioResponse != null) {
                sharedPreferences.saveUser(this@LoginActivity, usuarioResponse)
            }
            withContext(Dispatchers.Main) {
                if (usuarioResponse == null) {
                    mostrarErro()
                    binding.progressBar.visibility = View.GONE
                } else {
                    mostrarSucesso()
                    binding.progressBar.visibility = View.GONE
                    irParaAHome()
                }
            }
        }
    }

    private fun mostrarErro() {
        Toast.makeText(this@LoginActivity, "Email ou senha inválidos", Toast.LENGTH_SHORT).show()
        binding.email.setError("Email Inválido")
        binding.senha.setError("Senha Inválida")
    }

    private fun mostrarSucesso() {
        Toast.makeText(this@LoginActivity, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show()
    }

    private fun irParaAHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun irParaATelaDeCadastro() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }

}