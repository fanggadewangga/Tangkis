package com.college.tangkis.core.di

import com.college.tangkis.data.source.remote.api.service.ApiService
import com.college.tangkis.feature.main.constant.Constants.BASE_URL
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    @Singleton
    fun provideTangkisApi(): ApiService {
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
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}