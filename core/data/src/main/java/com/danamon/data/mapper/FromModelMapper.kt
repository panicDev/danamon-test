package com.danamon.data.mapper

interface FromModelMapper<MODEL, ENTITY> {
    fun fromModel(model: MODEL): ENTITY
}