package com.lp4.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val state = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state

    fun validarInputs(email: String?, senha:String?) {
        if (email.toString().isNullOrEmpty() && senha.toString().isNullOrEmpty()) {
            state.value = ViewState.ShowError
        } else if ((email.toString().isEmpty())) {
            state.value = ViewState.ShowError
        } else if (senha.toString().isEmpty()) {
            state.value = ViewState.ShowError
        } else {
            state.value = ViewState.ShowSuccess
        }

    }
}

sealed class ViewState {
    object ShowError : ViewState()
    object ShowErrorEmail : ViewState()
    object ShowErrorPassword : ViewState()
    object ShowSuccess : ViewState()
}