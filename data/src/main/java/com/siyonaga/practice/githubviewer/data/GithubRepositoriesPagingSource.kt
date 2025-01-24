package com.siyonaga.practice.githubviewer.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.siyonaga.practice.githubviewer.model.GithubRepository
import com.siyonaga.practice.githubviewer.model.Owner

internal class GithubRepositoriesPagingSource(
    private val githubRepositoryApiService: GithubRepositoryApiService,
    private val word: String,
    private val order: String,
) : PagingSource<Int, GithubRepository>() {
    companion object {
        const val PER_PAGE = 30
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepository> {
        val nowPage = params.key ?: 1
        val githubRepositories = githubRepositoryApiService.getAllGithubRepositories(
            word = word,
            order = order,
            perPage = PER_PAGE,
            nowPage = nowPage,
        ).items

        return runCatching {
            githubRepositories.map {
                GithubRepository(
                    name = it.name,
                    repositoryPath = it.fullName,
                    owner = Owner(it.owner.login, it.owner.avatarUrl),
                    stargazersCount = it.stargazersCount,
                )
            }
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
