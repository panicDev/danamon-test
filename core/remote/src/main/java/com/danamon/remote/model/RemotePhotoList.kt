package com.danamon.remote.model

import com.danamon.data.entity.PhotoListEntity
import com.danamon.data.mapper.FromEntityMapper
import com.danamon.data.mapper.ToEntityMapper

class RemotePhotoList(
    var data: List<RemotePhoto>? = null
) : ToEntityMapper<PhotoListEntity> {
    override fun toEntity() = PhotoListEntity(
        data = data?.map { it.toEntity() }.orEmpty()
    )

    companion object : FromEntityMapper<PhotoListEntity, RemotePhotoList> {
        override fun fromEntity(entity: PhotoListEntity?) = RemotePhotoList(
            data = entity?.data?.map { RemotePhoto.fromEntity(it) }
        )
    }
}