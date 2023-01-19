package com.lp4.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.lp4.HomeActivity
import com.lp4.profile.ProfileActivity
import com.lp4.databinding.LoginActivityBinding
import com.lp4.login.presentation.LoginViewModel
import com.lp4.login.presentation.ViewState

open class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
                ViewState.ShowSuccess -> irParaAHome()
            }
        }
    }

    private fun configLoginButton() {
        binding.buttomLogin.setOnClickListener {
            viewModel.validarInputs(
                email = binding.email.text.toString(),
                senha = binding.senha.text.toString(),
            )
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
}
