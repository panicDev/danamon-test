package com.danamon.local.di

import android.content.Context
import androidx.room.Room
import com.danamon.common.utils.PreferenceInfo
import com.danamon.data.datasource.local.LocalDataSource
import com.danamon.local.db.AppDatabase
import com.danamon.local.source.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @PreferenceInfo
    fun providePrefName(): String = "DANAMON_PREFS"

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "DNMN_DB")
            .build()

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource {
        return localDataSourceImpl
    }
}