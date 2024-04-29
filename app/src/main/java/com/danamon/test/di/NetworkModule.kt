package com.danamon.test.di

import com.danamon.common.base.WebApiProvider
import com.danamon.test.DanamonApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideWebApiProvider(
        myApplication: DanamonApp
    ) : WebApiProvider = WebApiProvider(myApplication.applicationContext)

    @Singleton
    @Provides
    fun provideRetrofit(
        webApiProvider: WebApiProvider,
        myApplication: DanamonApp,
    ): Retrofit = webApiProvider.getRetrofit(myApplication.getBaseUrl())
}