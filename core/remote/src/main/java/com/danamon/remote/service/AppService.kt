package com.danamon.remote.service

import com.danamon.data.entity.PhotoListEntity
import com.danamon.remote.base.BaseResponse
import com.danamon.remote.model.RemotePhotoList
import retrofit2.http.GET

interface AppService {
    @GET("/photos")
    suspend fun getPhotos(): BaseResponse<RemotePhotoList, PhotoListEntity>
}