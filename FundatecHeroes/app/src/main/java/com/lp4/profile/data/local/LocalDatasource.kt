package com.lp4.profile.data.local

import android.util.Log
import com.lp4.database.FHDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDatasource {
    private val database: FHDatabase by lazy {
        FHDatabase.getInstance()
    }

    suspend fun save() {
        return withContext(Dispatchers.IO) {
            database.userDao().insertUser(
                UserEntity(
                    name = "Luiz Henrique",
                    email = "luizlhra24@gmail.com",
                    password = "123456"
                )
            )
            Log.e("teste", "${database.userDao().getUser()}")
        }
    }
}