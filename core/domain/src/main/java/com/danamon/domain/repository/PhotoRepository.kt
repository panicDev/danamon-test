package com.danamon.domain.repository

import com.danamon.domain.datastate.DataState
import com.danamon.domain.model.PhotoList

interface PhotoRepository {
    suspend fun getPhotos(): DataState<PhotoList>
}