package com.lp4.profile.data.local

import androidx.room.Insert
import androidx.room.Query

interface UserDao {
    @Insert
    fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user")
    fun getUser(): List<UserEntity>
}