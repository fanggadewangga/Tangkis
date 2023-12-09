package com.college.tangkis.core.di

import android.content.Context
import androidx.room.Room
import com.college.tangkis.core.util.Constants
import com.college.tangkis.data.source.local.datastore.TangkisDatastore
import com.college.tangkis.data.source.local.room.TangkisDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    fun provideDatastore(
        @ApplicationContext context: Context,
    ) = TangkisDatastore(context)

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TangkisDatabase {
        return Room.databaseBuilder(
            context,
            TangkisDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }
}