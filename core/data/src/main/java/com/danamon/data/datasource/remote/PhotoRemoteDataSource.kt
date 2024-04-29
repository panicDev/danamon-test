package com.danamon.data.datasource.remote

import com.danamon.data.base.ResponseEntity
import com.danamon.data.entity.PhotoListEntity

interface PhotoRemoteDataSource {
    suspend fun getPhotos(): ResponseEntity<PhotoListEntity>
}