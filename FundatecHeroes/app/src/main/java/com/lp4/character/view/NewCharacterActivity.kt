package com.lp4.character.view

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lp4.api.CharacterClient
import com.lp4.character.presentation.NewCharacterViewModel
import com.lp4.database.SharedPreferencesUtils
import com.lp4.databinding.ActivityNewCharacterBinding
import com.lp4.home.HomeActivity
import com.lp4.model.Character
import kotlinx.coroutines.*

class NewCharacterActivity : AppCompatActivity() {

    private val viewModel: NewCharacterViewModel by viewModels()
    private lateinit var binding: ActivityNewCharacterBinding
    private val sharedPreferences: SharedPreferencesUtils by lazy {
        SharedPreferencesUtils()
    }

    val comics = arrayOf("Editora", "DC", "MARVEL")
    val type = arrayOf("Tipo", "HERO", "VILLAIN")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinners()
        observeViewModel()

        binding.buttonEnviar.setOnClickListener {
            configButtonCriarPersonagem()
        }
    }

    private fun setupSpinners() {
        binding.spinnerMarvelDc.adapter =
            ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, comics)
        binding.spinnerHeroiVilao.adapter =
            ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, type)
    }

    private fun configButtonCriarPersonagem() {
        if (validateCharacter()) {
            viewModel.validarPersonagem(this.getCharacter())
            SaveData()
            goToHome()
        } else {
            mostrarErro()
        }
    }

    private fun observeViewModel() {
        viewModel.viewState.observe(this) { state ->
            when (state) {
                is NewCharacterViewModel.ViewState.ShowError -> mostrarErro()
                is NewCharacterViewModel.ViewState.ShowSuccess -> mostrarSucesso()
            }
        }
    }

    private fun mostrarErro() {
        Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarSucesso() {
        Toast.makeText(this, "Personagem criado com sucesso!", Toast.LENGTH_SHORT).show()
    }

    private fun SaveData() {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val characterClient = CharacterClient()
            characterClient.createCharacter(
                sharedPreferences.getUser(this@NewCharacterActivity).toString(),
                getCharacter()
            )
        }
    }

    private fun goToHome() {
       startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun getCharacter(): Character {
        val nome = binding.inputNome.text.toString()
        val descricao = binding.inputDescricao.text.toString()
        val idade = binding.inputIdade.text.toString()
        val link = binding.inputLink.text.toString()
        val data = binding.inputData.text.toString()
        val editora = binding.spinnerMarvelDc.selectedItem.toString()
        val tipo = binding.spinnerHeroiVilao.selectedItem.toString()
        return Character(nome, descricao, link, editora, tipo, idade.toDouble(), data)
    }

    private fun validateCharacter(): Boolean {
        val character = getCharacter()
        return !(character.name.isBlank() || character.description.isBlank() || character.age.toString()
            .isBlank() ||
                character.image.isBlank() || character.date.isBlank() || character.universeType == "Editora" ||
                character.characterType == "Tipo")
    }
}