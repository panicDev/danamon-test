package com.danamon.data.mapper

interface ToEntityMapper<ENTITY> {
    fun toEntity(): ENTITY
}