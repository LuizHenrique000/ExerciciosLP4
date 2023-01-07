package com.lp4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val botao_login = findViewById<Button>(R.id.buttom_login)
        val cadastro = findViewById<TextView>(R.id.novo_por_aqui)

        botao_login.setOnClickListener(){
            irParaAHome()
        }

        cadastro.setOnClickListener(){
            irParaATelaDeCadastro()
       }
    }

    fun irParaAHome(){
        val telaHome = Intent(this, HomeActivity::class.java)
        startActivity(telaHome)
    }

    fun irParaATelaDeCadastro(){
        val telaCadastro = Intent(this, ProfileActivity::class.java)
        startActivity(telaCadastro)
    }
}
