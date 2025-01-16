package com.siyonaga.practice.githubviewer.repository

interface GithubRepositoryRepository {
    suspend fun getAllGithubRepositories(): List<Any> // TODO: この後対応予定
    suspend fun getCurrentGithubRepository(): Any // TODO: この後対応予定
}
