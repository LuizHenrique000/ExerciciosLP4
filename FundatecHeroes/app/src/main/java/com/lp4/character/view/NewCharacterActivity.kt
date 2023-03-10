package com.lp4.character.view

import android.R
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lp4.character.presentation.NewCharacterViewModel
import com.lp4.databinding.ActivityNewCharacterBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class NewCharacterActivity : AppCompatActivity() {

    private val viewModel: NewCharacterViewModel by viewModels()
    private lateinit var binding: ActivityNewCharacterBinding

    private val moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    val comics = arrayOf("Editora","DC","Marvel")
    val type = arrayOf("Tipo","Heroi","Vilão","Anti Heroi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCharacterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonEnviar.setOnClickListener() {
        }

        configButtonCriarPersonagem()
        viewModel.viewState.observe(this) { state ->
            when (state) {
                is
                NewCharacterViewModel.ViewState.ShowError -> mostrarErro()
                NewCharacterViewModel.ViewState.ShowSuccess -> {}
            }

            val spinnerComics = binding.spinnerMarvelDc
            val spinnerType = binding.spinnerHeroiVilao
            val arrayAdapter = ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item, comics)
            val arrayAdapter2 = ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item, type)
            spinnerComics.adapter = arrayAdapter
            spinnerType.adapter = arrayAdapter2
        }

    }

    private fun configButtonCriarPersonagem(){
        binding.buttonEnviar.setOnClickListener(){
            viewModel.validarInputs(
                nome = binding.inputNome.text.toString(),
                descricao = binding.inputDescricao.text.toString(),
                idade = binding.inputIdade.text.toString(),
                data = binding.inputData.text.toString(),
                link = binding.inputLink.text.toString()
            )
        }
    }

    private fun mostrarErro() {
        binding.inputNome.setError("Nome Inválido")
        binding.inputDescricao.setError("Descrição Inválida")
        binding.inputIdade.setError("Idade Inválida")
        binding.inputData.setError("Data Inválida")
        binding.inputLink.setError("Link Inválido")
    }

    data class Character(
        val name: String,
        val description: String,
        val age: Int,
        val date: String,
        val link: String
    )


}
