package com.siyonaga.practice.githubviewer.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.siyonaga.practice.githubviewer.model.GithubRepository
import com.siyonaga.practice.githubviewer.model.GithubRepositoryDetail
import com.siyonaga.practice.githubviewer.repository.GithubRepositoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GithubRepositoryRepositoryImpl @Inject constructor(
    private val githubRepositoryApiService: GithubRepositoryApiService,
) : GithubRepositoryRepository {
    override suspend fun getAllGithubRepositories(
        word: String,
        order: String,
    ): Flow<PagingData<GithubRepository>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
            ),
        ) {
            GithubRepositoriesPagingSource(
                githubRepositoryApiService,
                word,
                order,
            )
        }.flow
    }

    override suspend fun getCurrentGithubRepository(
        ownerName: String,
        repoName: String,
    ): GithubRepositoryDetail {
        val githubRepositoryDetail = githubRepositoryApiService.getCurrentGithubRepository(
            ownerName = ownerName,
            repoName = repoName,
        )

        return GithubRepositoryDetail(readmeText = githubRepositoryDetail.readmeText)
    }
}
