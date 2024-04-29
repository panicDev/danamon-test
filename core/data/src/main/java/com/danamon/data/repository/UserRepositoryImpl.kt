package com.danamon.data.repository

import com.danamon.common.ext.emptyInt
import com.danamon.common.ext.orEmpty
import com.danamon.data.datasource.local.LocalDataSource
import com.danamon.data.entity.UserEntity
import com.danamon.domain.model.User
import com.danamon.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
) : UserRepository {

    override suspend fun login(email: String, password: String, role: String): User {
        val user = dataSource.login(email, password, role)
        return User(
            user.id.orEmpty(),
            user.username.orEmpty(),
            user.email.orEmpty(),
            user.role.orEmpty()
        )
    }

    override suspend fun registerUser(
        username: String,
        email: String,
        role: String,
        password: String
    ) {
        dataSource.insertUser(
            UserEntity(emptyInt(), username, email, role, password)
        )
    }

    override suspend fun showAllUser(): List<User> {
        return dataSource.getAllUser().map { User(it.id, it.username, it.email, it.role) }
    }

    override suspend fun update(id: Int, username: String, email: String, role: String) {
        dataSource.update(id, username, email, role)
    }

    override suspend fun deleteUser(id: Int) {
        dataSource.deleteUser(id)
    }

    override suspend fun deleteAllUser() {
        dataSource.deleteAllUser()
    }

    override fun setUserLoggedIn(loggedIn: Boolean) {
        dataSource.setIsLoggedIn(loggedIn)
    }

    override fun setUserRole(role: String) {
        dataSource.setRole(role)
    }

    override fun isUserLoggedIn(): Boolean {
        return dataSource.isLoggedIn()
    }

    override fun userRole(): String {
        return dataSource.getUserRole()
    }
}