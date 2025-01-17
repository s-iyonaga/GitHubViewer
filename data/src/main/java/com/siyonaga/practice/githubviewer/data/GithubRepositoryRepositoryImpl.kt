package com.siyonaga.practice.githubviewer.data

import com.siyonaga.practice.githubviewer.model.GithubRepository
import com.siyonaga.practice.githubviewer.model.GithubRepositoryDetail
import com.siyonaga.practice.githubviewer.model.Owner
import com.siyonaga.practice.githubviewer.repository.GithubRepositoryRepository
import javax.inject.Inject

internal class GithubRepositoryRepositoryImpl @Inject constructor(
    private val githubRepositoryApiService: GithubRepositoryApiService,
) : GithubRepositoryRepository {
    override suspend fun getAllGithubRepositories(
        word: String,
        order: String,
        perPage: Int,
        nowPage: Int,
    ): List<GithubRepository> {
        val apiService = githubRepositoryApiService.getAllGithubRepositories(
            word = word,
            order = order,
            perPage = perPage,
            nowPage = nowPage,
        ).items

        return apiService.map {
            GithubRepository(
                name = it.name,
                repositoryPath = it.fullName,
                owner = Owner(it.owner.login, it.owner.avatarUrl),
                stargazersCount = it.stargazersCount,
            )
        }
    }

    override suspend fun getCurrentGithubRepository(
        ownerName: String,
        repoName: String,
    ): GithubRepositoryDetail {
        val apiService = githubRepositoryApiService.getCurrentGithubRepository(
            ownerName = ownerName,
            repoName = repoName,
        )

        return GithubRepositoryDetail(readmeText = apiService.readmeText)
    }
}
