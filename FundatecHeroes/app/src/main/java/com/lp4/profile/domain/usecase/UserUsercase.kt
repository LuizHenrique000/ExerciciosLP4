package com.lp4.profile.domain.usecase

import com.lp4.profile.data.repository.UserRepository

class UserUsercase {
    private val repository: UserRepository by lazy {
        UserRepository()
    }

    suspend fun saveUser(){
        repository.saveUser()
    }
}