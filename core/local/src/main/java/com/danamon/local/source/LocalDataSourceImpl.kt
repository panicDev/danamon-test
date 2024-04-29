package com.danamon.local.source

import android.app.Application
import android.content.Context
import com.danamon.common.ext.emptyString
import com.danamon.common.utils.PreferenceInfo
import com.danamon.data.datasource.local.LocalDataSource
import com.danamon.data.entity.UserEntity
import com.danamon.local.dao.UserDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    app: Application,
    private val dao: UserDao,
    @PreferenceInfo private val prefName: String
) : LocalDataSource {

    companion object{
        const val PREF_KEY_IS_LOGGED_IN = "IS_LOGGED_IN_KEY"
        const val PREF_KEY_USER_ROLE = "ROLE_KEY"
    }

    private val prefs = app.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    override fun isLoggedIn(): Boolean = prefs.getBoolean(PREF_KEY_IS_LOGGED_IN, false)
    override fun getUserRole(): String = prefs.getString(PREF_KEY_USER_ROLE, emptyString()) ?: emptyString()

    override fun setIsLoggedIn(isLoggedIn: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(PREF_KEY_IS_LOGGED_IN, isLoggedIn)
        editor.apply()
    }

    override fun setRole(role: String) {
        val editor = prefs.edit()
        editor.putString(PREF_KEY_USER_ROLE, role)
        editor.apply()
    }

    override suspend fun login(email: String, password: String, role: String): UserEntity =
        dao.login(email, password, role)

    override suspend fun insertUser(user: UserEntity) {
        dao.insert(user)
    }

    override suspend fun update(id: Int, username: String, email: String, role: String) {
        dao.update(id, username, email, role)
    }

    override suspend fun deleteAllUser() {
        dao.deleteAll()
    }

    override suspend fun deleteUser(id: Int) {
        dao.delete(id)
    }

    override suspend fun getAllUser(): List<UserEntity> =
        dao.getAll()

    override suspend fun getUser(id: Int): UserEntity =
        dao.get(id)

}