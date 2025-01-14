package com.siyonaga.practice.githubviewer.repository

interface GithubRepositoryRepository {
    suspend fun getAllGithubRepositories(): GithubRepositories
    suspend fun getCurrentGithubRepository(): GithubRepository
}
