package com.danamon.data.entity

import com.danamon.data.mapper.FromModelMapper
import com.danamon.data.mapper.ToModelMapper
import com.danamon.domain.model.PhotoList

data class PhotoListEntity(
    var data: List<PhotoEntity>? = null
) : ToModelMapper<PhotoList> {
    override fun toModel() = PhotoList(
        data = data?.map { it.toModel() }.orEmpty()
    )

    companion object : FromModelMapper<PhotoList, PhotoListEntity> {
        override fun fromModel(model: PhotoList) = PhotoListEntity(
            data = model.data.map { PhotoEntity.fromModel(it) }
        )
    }
}