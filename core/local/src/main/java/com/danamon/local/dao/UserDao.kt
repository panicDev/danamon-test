package com.danamon.local.dao

import androidx.room.*
import com.danamon.data.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    suspend fun getAll(): List<UserEntity>

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun get(id: Int): UserEntity

    @Query("SELECT * FROM User WHERE email = :email AND password = :password AND role = :role")
    suspend fun login(email: String, password: String, role: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Query("UPDATE user SET username = :username, email = :email, role = :role WHERE id = :id")
    suspend fun update(id: Int, username: String, email: String, role: String)

    @Query("DELETE FROM User")
    suspend fun deleteAll()

    @Query("DELETE FROM User WHERE id = :id")
    suspend fun delete(id: Int)
}