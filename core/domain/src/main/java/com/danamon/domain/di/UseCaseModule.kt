package com.danamon.domain.di

import com.danamon.domain.repository.PhotoRepository
import com.danamon.domain.repository.UserRepository
import com.danamon.domain.usecase.PhotoUseCase
import com.danamon.domain.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideUserUseCase(userRepository: UserRepository): UserUseCase {
        return UserUseCase(userRepository)
    }

    @Singleton
    @Provides
    fun providePhotoUseCase(photoRepository: PhotoRepository): PhotoUseCase {
        return PhotoUseCase(photoRepository)
    }
}
