package com.behzoddev.hilttutorialwithmindorks.di

import android.util.Log
import com.behzoddev.hilttutorialwithmindorks.BuildConfig
import com.behzoddev.hilttutorialwithmindorks.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // #1 Approach
    @Provides
    @Singleton
    fun provideRetrofitInstance(
        client: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): ApiService {
        Log.d("Debug","provideRetrofitInstance is created")
        return Retrofit.Builder().run {
            baseUrl(BuildConfig.BASE_URL)
            addConverterFactory(moshiConverterFactory)
            client(client)
            build()
        }.run {
            Log.d("Debug","ApiService is created")
            create(ApiService::class.java)
        }
    }
    // #2 Approach
    /*
    @Provides
    @Singleton
    fun providesRetrofitInstance(
        client: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .client(client)
            .build()
    }
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    */

    @Provides
    @Singleton

    fun provideMoshiConvertor() : MoshiConverterFactory {
        Log.d("Debug","provideMoshiConvertor is created")
        return MoshiConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient().apply {
        Log.d("Debug","provideOkHttpClient is created")
        OkHttpClient.Builder().run {
            addInterceptor(loggingInterceptor)
            build()
        }
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        Log.d("Debug","provideLoggingInterceptor is created")
        level = HttpLoggingInterceptor.Level.BODY
    }
}