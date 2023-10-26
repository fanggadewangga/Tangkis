package com.college.tangkis.core.di

import com.college.tangkis.data.source.remote.ApiService
import com.college.tangkis.feature.main.constant.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    @Singleton
    fun provideStuntionApi(): ApiService {
        val client: OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor {
                    val original = it.request()
                    val requestBuilder = original.newBuilder()
                    val request = requestBuilder.build()
                    it.proceed(request)
                }
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}