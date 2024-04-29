package com.danamon.data.mapper

interface ToModelMapper<MODEL> {
    fun toModel(): MODEL
}