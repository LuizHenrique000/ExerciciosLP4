package com.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SplashScreen();
        setContentView(R.layout.activity_main)
        val botaoPrimeiraTela = findViewById<Button>(R.id.botao_primeira_tela);

        botaoPrimeiraTela.setOnClickListener(){
            IrParaSegundaTela();
        }

    }

    private fun IrParaSegundaTela(){
        val segundaTela = Intent(this, SegundaTela::class.java)
        startActivity(segundaTela)
    }

    private fun SplashScreen(){
        Thread.sleep(2000)
        setTheme(R.style.Theme_MyApplication)
    }
}