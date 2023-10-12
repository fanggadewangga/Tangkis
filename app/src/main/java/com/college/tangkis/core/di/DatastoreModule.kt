package com.college.tangkis.core.di

import android.content.Context
import com.college.tangkis.data.source.local.TangkisDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatastoreModule {
    @Provides
    fun provideDatastore(
        @ApplicationContext context: Context,
    ) = TangkisDatastore(context)
}