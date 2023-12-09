package com.college.tangkis.core.di

import com.college.tangkis.data.source.local.LocalDataSource
import com.college.tangkis.data.source.local.datastore.TangkisDatastore
import com.college.tangkis.data.source.local.room.TangkisDatabase
import com.college.tangkis.data.source.remote.RemoteDataSource
import com.college.tangkis.data.source.remote.api.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Provides
    fun provideRemoteDataSource(
        apiService: ApiService
    ) = RemoteDataSource(apiService)

    @Provides
    fun provideLocalDataSource(
        db: TangkisDatabase,
        datastore: TangkisDatastore
    ) = LocalDataSource(datastore, db.dao)
}