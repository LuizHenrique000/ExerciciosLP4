package com.lp4.character

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.lp4.R
import com.lp4.databinding.ActivityNewCharacterBinding

class NewCharacter : AppCompatActivity() {

    private lateinit var binding: ActivityNewCharacterBinding

    val comics = arrayOf("Editora","DC","Marvel")
    val type = arrayOf("Tipo","Heroi","Vilão","Anti Heroi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCharacterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonEnviar.setOnClickListener(){
            salvarDados()
        }

        val spinnerComics = binding.spinnerMarvelDc
        val spinnerType = binding.spinnerHeroiVilao

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, comics)
        val arrayAdapter2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, type)
        spinnerComics.adapter = arrayAdapter
        spinnerType.adapter = arrayAdapter2
    }

    private fun salvarDados() {
        val nome = binding.inputNome.getText().toString();
        val descricao = binding.inputDescricao.getText().toString();
        val idade = binding.inputIdade.getText().toString();
        val data = binding.inputData.getText().toString();
        val link = binding.inputLink.getText().toString();

        val sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString("nome",nome)
            putString("descricao", descricao)
            putString("idade", idade)
            putString("data", data)
            putString("link", link)
            apply()
        }

    }
}