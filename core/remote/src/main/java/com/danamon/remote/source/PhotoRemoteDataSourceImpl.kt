package com.danamon.remote.source

import com.danamon.data.base.ResponseEntity
import com.danamon.data.datasource.remote.PhotoRemoteDataSource
import com.danamon.data.entity.PhotoListEntity
import com.danamon.remote.service.AppService
import javax.inject.Inject

class PhotoRemoteDataSourceImpl @Inject constructor(
    private val service: AppService
) : PhotoRemoteDataSource {
    override suspend fun getPhotos(): ResponseEntity<PhotoListEntity> {
        return service.getPhotos().toEntityWithData { it?.toEntity() }
    }
}