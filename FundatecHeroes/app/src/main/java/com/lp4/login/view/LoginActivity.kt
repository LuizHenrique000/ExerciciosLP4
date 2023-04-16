package com.lp4.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.lp4.api.Usuario
import com.lp4.api.UsuarioClient
import com.lp4.home.HomeActivity
import com.lp4.profile.ProfileActivity
import com.lp4.databinding.LoginActivityBinding
import com.lp4.login.presentation.LoginViewModel
import com.lp4.login.presentation.ViewState
import com.lp4.model.UsuarioRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.novoPorAqui.setOnClickListener() {
            irPAraTeste()
        }

        configLoginButton()
        viewModel.viewState.observe(this) { state ->
            when (state) {
                is
                ViewState.ShowError -> mostrarErro()
                ViewState.ShowErrorEmail -> {}
                ViewState.ShowErrorPassword -> {}
                ViewState.ShowSuccess -> irParaAHome()
                else -> {}
            }
        }
    }

    private fun configLoginButton() {
        binding.buttomLogin.setOnClickListener {
            viewModel.validarInputs(
                email = binding.email.text.toString(),
                senha = binding.senha.text.toString(),
            )
            val email = binding.email.text.toString()
            val password = binding.senha.text.toString()

            val scope = CoroutineScope(Dispatchers.IO)

            scope.launch {
                val apiClient = UsuarioClient()
                val usuarioResponse = apiClient.getUser(email, password)
                println(usuarioResponse)
                withContext(Dispatchers.Main) {
                    if (usuarioResponse != null) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Login efetuado com sucesso",
                            Toast.LENGTH_SHORT
                        ).show()
                        irParaAHome()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Email ou senha inválidos",
                            Toast.LENGTH_SHORT
                        ).show()
                        mostrarErro()
                    }
                }
            }
        }
    }

    private fun mostrarErro() {
        binding.email.setError("Email Inválido")
        binding.senha.setError("Senha Inválida")
    }

    private fun irParaAHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun irParaATelaDeCadastro() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }

    private fun irPAraTeste() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }
}
