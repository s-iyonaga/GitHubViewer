package com.siyonaga.practice.githubviewer.data

import com.siyonaga.practice.githubviewer.repository.GithubRepositoryRepository
import javax.inject.Inject

class GithubRepositoryRepositoryImpl @Inject constructor(
    private val githubRepositoryApiService: GithubRepositoryApiService,
) : GithubRepositoryRepository {

    override suspend fun getAllGithubRepositories(
        word: String,
        order: String,
        perPage: Int,
        nowPage: Int,
    ): List<GithubRepository> {
        return githubRepositoryApiService.getAllGithubRepositories(
            word = word,
            order = order,
            perPage = perPage,
            nowPage = nowPage,
        )
        // returnType: GithubRepositories
    }

    override suspend fun getCurrentGithubRepository(
        ownerName: String,
        repoName: String,
    ): GithubRepositoryDetail {
        return githubRepositoryApiService.getCurrentGithubRepository(
            ownerName = ownerName,
            repoName = repoName,
        )
    }
}
