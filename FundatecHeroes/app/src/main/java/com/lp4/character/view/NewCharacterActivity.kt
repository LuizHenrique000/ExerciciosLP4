package com.lp4.character.view

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.lp4.api.PersonagemClient
import com.lp4.character.presentation.NewCharacterViewModel
import com.lp4.database.SharedPreferencesUtils
import com.lp4.databinding.ActivityNewCharacterBinding
import com.lp4.home.HomeActivity
import com.lp4.model.Personagem
import com.lp4.model.UsuarioResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewCharacterActivity : AppCompatActivity() {

    private val viewModel: NewCharacterViewModel by viewModels()
    private lateinit var binding: ActivityNewCharacterBinding

    val comics = arrayOf("Editora", "DC", "MARVEL")
    val type = arrayOf("Tipo", "HERO", "VILLAIN")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinners()
        configButtonCriarPersonagem()
        observeViewModel()
    }

    private fun setupSpinners() {
        binding.spinnerMarvelDc.adapter =
            ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, comics)
        binding.spinnerHeroiVilao.adapter =
            ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, type)
    }

    private fun configButtonCriarPersonagem() {
        binding.buttonEnviar.setOnClickListener() {
            if (validarDadosPersonagem()) {
                viewModel.validarPersonagem(this.getPersonagem())
                salvarDados()
            } else {
                mostrarErro()
            }
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
        Toast.makeText(this, "Preenca todos os campos", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarSucesso() {
        salvarDados()
        Toast.makeText(this, "Personagem criado com sucesso", Toast.LENGTH_SHORT).show()
    }

    private fun salvarDados() {
        val personagem = getPersonagem()
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
           val id = SharedPreferencesUtils.getUser(this@NewCharacterActivity)
            val personagemClient = PersonagemClient()
            personagemClient.createPersonagem(id.toString(), personagem)
            withContext(Dispatchers.Main) {
                mostrarSucesso()
                irParaHome()
            }
        }
    }


    private fun irParaHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun getPersonagem(): Personagem {
        val nome = binding.inputNome.text.toString()
        val descricao = binding.inputDescricao.text.toString()
        val idade = binding.inputIdade.text.toString()
        val link = binding.inputLink.text.toString()
        val data = binding.inputData.text.toString()
        val editora = binding.spinnerMarvelDc.selectedItem.toString()
        val tipo = binding.spinnerHeroiVilao.selectedItem.toString()
        val personagem = Personagem(nome, descricao, link, editora, tipo, idade.toDouble(), data)
        return personagem
    }

    private fun validarDadosPersonagem(): Boolean {
        val personagem = getPersonagem()
        return !(personagem.name.isBlank() || personagem.description.isBlank() || personagem.age.toString()
            .isBlank() ||
                personagem.image.isBlank() || personagem.date.isBlank() || personagem.universeType == "Editora" ||
                personagem.characterType == "Tipo")
    }

}