package com.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SegundaTela : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SplashScreen();
        setContentView(R.layout.activity_segunda_tela)
        val botaoSegundaTela = findViewById<Button>(R.id.botao_segunda_tela);

    botaoSegundaTela.setOnClickListener(){
        IrParaPrimeiraTela();
    }

}
    private fun IrParaPrimeiraTela(){
    val primeiraTela = Intent(this, MainActivity::class.java)
    startActivity(primeiraTela)
}

    private fun SplashScreen(){
        setTheme(R.style.Theme_MyApplication)
    }

}