package com.siyonaga.practice.githubviewer.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.siyonaga.practice.githubviewer.model.GithubRepository
import com.siyonaga.practice.githubviewer.repository.GithubRepositoryRepository
import javax.inject.Inject

class GithubRepositoriesPagingSource @Inject constructor(
    private val githubRepositoryRepository: GithubRepositoryRepository,
    private val word: String,
    private val order: String,
    private val perPage: Int = 30,
) : PagingSource<Int, GithubRepository>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepository> {
        val nowPage = params.key ?: 1

        return runCatching {
            githubRepositoryRepository.getAllGithubRepositories(
                word = word,
                order = order,
                perPage = perPage,
                nowPage = nowPage,
            )
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = if (nowPage == 1) null else nowPage - 1,
                    nextKey = if (it.isEmpty()) null else nowPage + 1,
                )
            },
            onFailure = {
                LoadResult.Error(it)
            },
        )
    }

    override fun getRefreshKey(state: PagingState<Int, GithubRepository>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
