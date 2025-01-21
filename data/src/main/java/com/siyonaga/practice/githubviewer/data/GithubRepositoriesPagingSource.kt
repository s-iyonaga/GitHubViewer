package com.siyonaga.practice.githubviewer.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.siyonaga.practice.githubviewer.model.GithubRepository
import com.siyonaga.practice.githubviewer.repository.GithubRepositoryRepository
import javax.inject.Inject

class GithubRepositoriesPagingSource @Inject constructor(
    private val githubRepositoryRepository: GithubRepositoryRepository,
) : PagingSource<Int, GithubRepository>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepository> {
        try {
            val nowPage = params.key ?: 1
            val response = githubRepositoryRepository.getAllGithubRepositories(
                word = "android",
                order = "desc", // TODO: "asc"も指定できるように...
                perPage = 30,
                nowPage = nowPage,
            )

            return LoadResult.Page(
                data = response,
                prevKey = if (nowPage == 1) null else nowPage.minus(1),
                nextKey = if (response.isEmpty()) null else nowPage.plus(1),
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GithubRepository>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
