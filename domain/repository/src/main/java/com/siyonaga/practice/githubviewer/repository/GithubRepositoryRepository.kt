package com.siyonaga.practice.githubviewer.repository

import com.siyonaga.practice.githubviewer.model.GithubRepository
import com.siyonaga.practice.githubviewer.model.GithubRepositoryDetail

interface GithubRepositoryRepository {
    suspend fun getAllGithubRepositories(
        word: String,
        order: String,
        perPage: Int,
        nowPage: Int,
    ): List<GithubRepository>

    suspend fun getCurrentGithubRepository(
        ownerName: String,
        repoName: String,
    ): GithubRepositoryDetail
}
