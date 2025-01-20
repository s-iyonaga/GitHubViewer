package com.siyonaga.practice.githubviewer.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object GithubRepositoryApiServiceModule {
    @Provides
    @Singleton
    fun provideRetrofitJson(): Json {
        return Json { ignoreUnknownKeys = true }
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        retrofitJson: Json,
    ): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(retrofitJson.asConverterFactory("application/json".toMediaType()))
            .baseUrl("https://api.github.com")
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubRepositoryApiService(
        retrofit: Retrofit,
    ): GithubRepositoryApiService {
        return retrofit.create(GithubRepositoryApiService::class.java)
    }
}
