package com.danamon.remote.di

import com.danamon.data.datasource.remote.PhotoRemoteDataSource
import com.danamon.remote.service.AppService
import com.danamon.remote.source.PhotoRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun providePhotoDataSource(photoRemoteDataSourceImpl: PhotoRemoteDataSourceImpl) : PhotoRemoteDataSource {
        return photoRemoteDataSourceImpl
    }

    @Singleton
    @Provides
    fun providePhotoService(retrofit: Retrofit) : AppService = retrofit.create(
        AppService::class.java
    )
}