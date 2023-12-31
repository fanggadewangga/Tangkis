package com.college.tangkis.core.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        LocalModule::class,
        RepositoryModule::class,
        RemoteModule::class
    ]
)

@InstallIn(SingletonComponent::class)
object TangkisModule