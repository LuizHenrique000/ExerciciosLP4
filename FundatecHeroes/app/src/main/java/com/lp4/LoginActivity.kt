package com.lp4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.lp4.databinding.LoginActivityBinding
import com.lp4.presentation.LoginViewModel

open class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding){
           buttomLogin.setOnClickListener(){
                if (email.text.toString().isNullOrEmpty()){
                    email.setError("Email Inválido")
                } else {
                    irParaAHome()
                }
            }
        }

        binding.novoPorAqui.setOnClickListener(){
            irParaATelaDeCadastro()
        }
    }

   private fun irParaAHome(){
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun irParaATelaDeCadastro(){
        startActivity(Intent(this, ProfileActivity::class.java))
    }
}
