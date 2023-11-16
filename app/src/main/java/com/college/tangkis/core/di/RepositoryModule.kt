package com.college.tangkis.core.di

import com.college.tangkis.data.repository.activity.ActivityRepository
import com.college.tangkis.data.repository.activity.ActivityRepositoryImpl
import com.college.tangkis.data.repository.article.ArticleRepository
import com.college.tangkis.data.repository.article.ArticleRepositoryImpl
import com.college.tangkis.data.repository.consultation.ConsultationRepository
import com.college.tangkis.data.repository.consultation.ConsultationRepositoryImpl
import com.college.tangkis.data.repository.contact.ContactRepository
import com.college.tangkis.data.repository.contact.ContactRepositoryImpl
import com.college.tangkis.data.repository.report.ReportRepository
import com.college.tangkis.data.repository.report.ReportRepositoryImpl
import com.college.tangkis.data.repository.user.UserRepository
import com.college.tangkis.data.repository.user.UserRepositoryImpl
import com.college.tangkis.data.source.local.TangkisDatastore
import com.college.tangkis.data.source.remote.ApiService
import com.college.tangkis.data.source.remote.RemoteDataSource
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
        remoteDataSource: RemoteDataSource
    ): UserRepository = UserRepositoryImpl(datastore, remoteDataSource)

    @Provides
    fun provideArticleRepository(
        datastore: TangkisDatastore,
        apiService: ApiService,
    ): ArticleRepository = ArticleRepositoryImpl(datastore, apiService)

    @Provides
    fun provideConsultationRepository(
        datastore: TangkisDatastore,
        apiService: ApiService,
    ): ConsultationRepository = ConsultationRepositoryImpl(datastore, apiService)

    @Provides
    fun provideContactRepository(
        datastore: TangkisDatastore,
        apiService: ApiService,
    ): ContactRepository = ContactRepositoryImpl(datastore, apiService)

    @Provides
    fun provideReportRepository(
        datastore: TangkisDatastore,
        apiService: ApiService,
    ): ReportRepository = ReportRepositoryImpl(datastore, apiService)

    @Provides
    fun provideActivityRepository(
        datastore: TangkisDatastore,
        apiService: ApiService,
    ): ActivityRepository = ActivityRepositoryImpl(datastore, apiService)
}