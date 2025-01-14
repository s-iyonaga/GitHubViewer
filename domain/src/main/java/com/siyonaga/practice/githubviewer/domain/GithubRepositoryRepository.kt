package com.siyonaga.practice.githubviewer.domain

interface GithubRepositoryRepository {
    suspend fun getAllGithubRepositories(): GithubRepositories
    suspend fun getCurrentGithubRepository(): GithubRepository
}
