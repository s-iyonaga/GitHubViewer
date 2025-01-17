package com.siyonaga.practice.githubviewer.data

import com.siyonaga.practice.githubviewer.model.GithubRepository
import com.siyonaga.practice.githubviewer.model.GithubRepositoryDetail
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
        return githubRepositoryApiService.getAllGithubRepositories(
            word = word,
            order = order,
            perPage = perPage,
            nowPage = nowPage,
        ).items as List<GithubRepository> // TODO: 正しい実装（変換）を行えるようにする
    }

    override suspend fun getCurrentGithubRepository(
        ownerName: String,
        repoName: String,
    ): GithubRepositoryDetail {
        return githubRepositoryApiService.getCurrentGithubRepository(
            ownerName = ownerName,
            repoName = repoName,
        ) as GithubRepositoryDetail // TODO: 正しい実装（変換）を行えるようにする
    }
}
