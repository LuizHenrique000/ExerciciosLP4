package com.lp4.character.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lp4.model.User

class NewCharacterViewModel : ViewModel() {
    private val state = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state

    fun validarPersonagem (user: User) {
        if (user.name.isNullOrEmpty() || user.description.isNullOrEmpty() || user.age == null || user.date.isNullOrEmpty() || user.image.isNullOrEmpty() || user.universeType.isNullOrEmpty() || user.characterType.isNullOrEmpty() ) {
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