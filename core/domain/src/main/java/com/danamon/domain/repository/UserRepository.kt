package com.danamon.domain.repository

import com.danamon.domain.model.User

interface UserRepository {
    suspend fun login(email: String, password: String, role: String): User
    suspend fun registerUser(username: String, email: String, role: String, password: String)
    suspend fun showAllUser(): List<User>
    suspend fun update(id: Int, username: String, email: String, role: String)
    suspend fun deleteUser(id: Int)
    suspend fun deleteAllUser()
    fun setUserLoggedIn(loggedIn: Boolean)
    fun setUserRole(role: String)
    fun isUserLoggedIn(): Boolean
    fun userRole(): String
}