package com.danamon.data.repository

import com.danamon.data.datasource.DataStateBoundResource
import com.danamon.data.datasource.remote.PhotoRemoteDataSource
import com.danamon.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val dataSource: PhotoRemoteDataSource
) : PhotoRepository {
    override suspend fun getPhotos() = DataStateBoundResource.createNetworkCall {
        dataSource.getPhotos()
    }.getResult()
}