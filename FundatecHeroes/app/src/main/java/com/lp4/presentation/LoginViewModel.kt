package com.lp4.presentation

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    fun validarInputs(email: String?, senha:String?){
        if (email.toString().isNullOrEmpty() && senha.toString().isNullOrEmpty()) {
           
        }

    }
}