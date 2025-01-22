package com.siyonaga.practice.githubviewer.home

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.siyonaga.practice.githubviewer.model.GithubRepository
import kotlinx.coroutines.flow.Flow

@Stable
sealed interface HomeScreenUiState {
    @Immutable
    data object Loading : HomeScreenUiState

    @Stable
    data class Success(
        val order: String,
        val githubRepositoriesPagingData: Flow<PagingData<GithubRepository>>?,
    ) : HomeScreenUiState
}
