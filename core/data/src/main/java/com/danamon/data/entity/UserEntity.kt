package com.danamon.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danamon.common.ext.emptyInt
import com.danamon.common.ext.emptyString

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = emptyInt(),

    @ColumnInfo(name = "username")
    var username: String? = emptyString(),

    @ColumnInfo(name = "email")
    var email: String? = emptyString(),

    @ColumnInfo(name = "role")
    var role: String? = emptyString(),

    @ColumnInfo(name = "password")
    var password: String? = emptyString()
)