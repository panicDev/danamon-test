package com.danamon.remote.model

import com.danamon.common.ext.emptyInt
import com.danamon.common.ext.emptyString
import com.danamon.common.ext.orEmpty
import com.danamon.data.entity.PhotoEntity
import com.danamon.data.mapper.FromEntityMapper
import com.danamon.data.mapper.ToEntityMapper

data class RemotePhoto(
    var albumId: Int? = emptyInt(),
    var id: Int? = emptyInt(),
    var title: String? = emptyString(),
    var url: String? = emptyString(),
    var thumbnailUrl: String? = emptyString()
) : ToEntityMapper<PhotoEntity> {
    override fun toEntity() = PhotoEntity(
        albumId = albumId.orEmpty(),
        id = id.orEmpty(),
        title = title.orEmpty(),
        url = url.orEmpty(),
        thumbnailUrl = thumbnailUrl.orEmpty()
    )

    companion object : FromEntityMapper<PhotoEntity, RemotePhoto> {
        override fun fromEntity(entity: PhotoEntity?) = RemotePhoto(
            albumId = entity?.albumId,
            id = entity?.id,
            title = entity?.title,
            url = entity?.url,
            thumbnailUrl = entity?.thumbnailUrl
        )
    }
}
