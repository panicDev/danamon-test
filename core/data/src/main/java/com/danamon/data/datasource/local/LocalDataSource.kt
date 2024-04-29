package com.danamon.data.datasource.local

import com.danamon.data.entity.UserEntity

interface LocalDataSource {
    fun isLoggedIn(): Boolean
    fun setIsLoggedIn(isLoggedIn: Boolean)
    fun getUserRole(): String
    fun setRole(role: String)

    suspend fun login(email: String, password: String, role: String): UserEntity
    suspend fun insertUser(user: UserEntity)
    suspend fun getAllUser(): List<UserEntity>
    suspend fun getUser(id: Int): UserEntity
    suspend fun update(id: Int, username: String, email: String, role: String)
    suspend fun deleteAllUser()
    suspend fun deleteUser(id: Int)
}