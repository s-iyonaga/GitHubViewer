package com.siyonaga.practice.githubviewer.home

import androidx.paging.PagingData
import com.siyonaga.practice.githubviewer.model.GithubRepository
import kotlinx.coroutines.flow.Flow

sealed interface HomeScreenUiState {
    data object Loading : HomeScreenUiState
    data class Success(
        val order: String,
        val githubRepositoriesPagingData: Flow<PagingData<GithubRepository>>?,
    ) : HomeScreenUiState
}
