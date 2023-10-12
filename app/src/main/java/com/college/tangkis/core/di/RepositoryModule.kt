package com.college.tangkis.core.di

import com.college.tangkis.data.repository.user.UserRepository
import com.college.tangkis.data.repository.user.UserRepositoryImpl
import com.college.tangkis.data.source.local.TangkisDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideUserRepository(
        datastore: TangkisDatastore,
    ): UserRepository = UserRepositoryImpl(datastore)

}