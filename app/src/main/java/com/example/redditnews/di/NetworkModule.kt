package com.example.redditnews.di

import com.example.redditnews.core.network.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
//    @Provides
//    fun provideBaseUrl(): String = BuildConfig.APP_BASE_URL

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)
            baseUrl("https://www.reddit.com/")
        }.build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        requestInterceptor: RequestInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(requestInterceptor)
            addInterceptor(loggingInterceptor())
        }.build()
    }

    @Provides
    fun provideRequestInterceptor(): RequestInterceptor {
        return RequestInterceptor()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                HttpLoggingInterceptor.Level.BODY
        }
    }
}
