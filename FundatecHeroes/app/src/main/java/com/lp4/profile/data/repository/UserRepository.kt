package com.lp4.profile.data.repository

import com.lp4.profile.data.local.LocalDatasource

class UserRepository {

    private val localDataSource: LocalDatasource by lazy {
        LocalDatasource()
    }

    suspend fun saveUser() {
        localDataSource.save()
    }
}