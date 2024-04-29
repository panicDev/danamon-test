package com.danamon.data.di

import com.danamon.data.repository.PhotoRepositoryImpl
import com.danamon.data.repository.UserRepositoryImpl
import com.danamon.domain.repository.PhotoRepository
import com.danamon.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providePhotoRepository(photoRepositoryImpl: PhotoRepositoryImpl): PhotoRepository {
        return photoRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository {
        return userRepositoryImpl
    }
}
