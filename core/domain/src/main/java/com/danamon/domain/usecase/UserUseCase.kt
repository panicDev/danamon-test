package com.danamon.domain.usecase

import com.danamon.domain.model.User
import com.danamon.domain.repository.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend fun invokeLogin(email: String, password: String, role: String) : User {
        return repository.login(email, password, role)
    }

    suspend fun invokeRegister(username: String, email: String, role: String, password: String) {
        repository.registerUser(username, email, role, password)
    }

    suspend fun invokeShowListUser(): List<User> {
        return repository.showAllUser()
    }

    suspend fun invokeUpdate(id: Int, username: String, email: String, role: String) {
        repository.update(id, username, email, role)
    }

    suspend fun invokeDeleteUser(id: Int){
        repository.deleteUser(id)
    }

    suspend fun invokeDeleteAllUser(){
        repository.deleteAllUser()
    }

    fun invokeSetUserIsLoggedIn(isLoggedIn: Boolean) {
        repository.setUserLoggedIn(isLoggedIn)
    }

    fun invokeSetUserRole(role: String){
        repository.setUserRole(role)
    }

    fun invokeGetIsLoggedIn(): Boolean {
        return repository.isUserLoggedIn()
    }

    fun invokeGetUserRole(): String {
        return repository.userRole()
    }
}