package com.siyonaga.practice.githubviewer.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.siyonaga.practice.githubviewer.model.GithubRepository
import com.siyonaga.practice.githubviewer.model.GithubRepositoryDetail
import com.siyonaga.practice.githubviewer.model.Owner
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

internal class GithubRepositoryDataSource {
    private val retroJson = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(retroJson.asConverterFactory("application/json".toMediaType()))
        .baseUrl("https://api.github.com")
        .build()
    private val apiService = retrofit.create(GithubRepositoryApiService::class.java)

    suspend fun getAllGithubRepositories(
        word: String,
        order: String,
        perPage: Int,
        nowPage: Int,
    ): List<GithubRepository> {
        val tempGithubRepositories = apiService.getAllGithubRepositories(
            word = word,
            order = order,
            perPage = perPage,
            nowPage = nowPage,
        )

        return tempGithubRepositories.items.map {
            GithubRepository(
                name = it.name,
                repositoryPath = it.fullName,
                owner = Owner(it.owner.login, it.owner.avatarUrl),
                stargazersCount = it.stargazersCount,
            )
        }
    }

    suspend fun getCurrentGithubRepository(
        ownerName: String,
        repoName: String,
    ): GithubRepositoryDetail {
        val tempGithubRepositoryDetail = apiService.getCurrentGithubRepository(
            ownerName = ownerName,
            repoName = repoName,
        )

        return GithubRepositoryDetail(
            readmeText = tempGithubRepositoryDetail.readmeText,
        )
    }
}
