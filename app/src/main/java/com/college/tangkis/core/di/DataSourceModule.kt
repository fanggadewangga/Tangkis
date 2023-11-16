package com.college.tangkis.core.di

import com.college.tangkis.data.source.remote.api.service.ApiService
import com.college.tangkis.data.source.remote.RemoteDataSource
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
}