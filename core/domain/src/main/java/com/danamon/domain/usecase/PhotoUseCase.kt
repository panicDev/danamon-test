package com.danamon.domain.usecase

import com.danamon.domain.datastate.DataState
import com.danamon.domain.model.PhotoList
import com.danamon.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoUseCase @Inject constructor(private val photoRepository: PhotoRepository) {
    suspend fun invoke(): DataState<PhotoList> {
        return photoRepository.getPhotos()
    }
}
