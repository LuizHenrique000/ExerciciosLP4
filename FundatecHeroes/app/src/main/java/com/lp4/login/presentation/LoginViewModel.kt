package com.lp4.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lp4.api.hero.HeroClient
import com.lp4.api.user.UserClient
import com.lp4.databinding.LoginActivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {

    private lateinit var binding: LoginActivityBinding
    private val state = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state

    fun validarInputs(email: String?, senha: String?) {
        if (email.isNullOrEmpty() && senha.isNullOrEmpty()) {
            state.value = ViewState.ShowError
        } else if ((email.toString().isEmpty())) {
            state.value = ViewState.ShowErrorEmail
        } else if (senha.toString().isEmpty()) {
            state.value = ViewState.ShowErrorPassword
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