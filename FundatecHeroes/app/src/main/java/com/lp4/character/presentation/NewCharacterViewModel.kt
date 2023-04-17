package com.lp4.character.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lp4.model.Personagem

class NewCharacterViewModel : ViewModel() {
    private val state = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state

    fun validarPersonagem (personagem: Personagem) {
        if (personagem.name.isNullOrEmpty() || personagem.description.isNullOrEmpty() || personagem.age == null || personagem.date.isNullOrEmpty() || personagem.image.isNullOrEmpty() || personagem.universeType.isNullOrEmpty() || personagem.characterType.isNullOrEmpty() ) {
            state.value = ViewState.ShowError
        } else {
            state.value = ViewState.ShowSuccess
        }
    }

    sealed class ViewState {
        object ShowError : ViewState()
        object ShowSuccess : ViewState()
    }
}