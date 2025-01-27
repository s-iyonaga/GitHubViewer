package com.siyonaga.practice.githubviewer.repository

import androidx.paging.PagingData
import com.siyonaga.practice.githubviewer.model.GithubRepository
import com.siyonaga.practice.githubviewer.model.GithubRepositoryDetail
import com.siyonaga.practice.githubviewer.model.Order
import kotlinx.coroutines.flow.Flow

interface GithubRepositoryRepository {
    fun getAllGithubRepositories(
        word: String,
        order: Order,
    ): Flow<PagingData<GithubRepository>>

    suspend fun getCurrentGithubRepository(
        ownerName: String,
        repoName: String,
    ): GithubRepositoryDetail
}
