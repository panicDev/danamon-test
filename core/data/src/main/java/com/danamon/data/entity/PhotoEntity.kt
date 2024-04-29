package com.danamon.data.entity

import com.danamon.common.ext.emptyInt
import com.danamon.common.ext.emptyString
import com.danamon.common.ext.orEmpty
import com.danamon.data.mapper.FromModelMapper
import com.danamon.data.mapper.ToModelMapper
import com.danamon.domain.model.Photo

data class PhotoEntity(
    var albumId: Int? = emptyInt(),
    var id: Int? = emptyInt(),
    var title: String? = emptyString(),
    var url: String? = emptyString(),
    var thumbnailUrl: String? = emptyString()
) : ToModelMapper<Photo> {
    override fun toModel() = Photo(
        albumId = albumId.orEmpty(),
        id = id.orEmpty(),
        title = title.orEmpty(),
        url = url.orEmpty(),
        thumbnailUrl = thumbnailUrl.orEmpty()
    )

    companion object : FromModelMapper<Photo, PhotoEntity> {
        override fun fromModel(model: Photo) = PhotoEntity(
            albumId = model.albumId,
            id = model.id,
            title = model.title,
            url = model.url,
            thumbnailUrl = model.thumbnailUrl
        )
    }
}
