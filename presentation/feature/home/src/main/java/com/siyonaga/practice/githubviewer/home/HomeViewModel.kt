package com.siyonaga.practice.githubviewer.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.siyonaga.practice.githubviewer.model.GithubRepository
import com.siyonaga.practice.githubviewer.repository.GithubRepositoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val githubRepositoryRepository: GithubRepositoryRepository,
) : ViewModel() {
    private val order = MutableStateFlow(value = "desc")
    private val githubRepositoriesPagingData =
        MutableStateFlow<Flow<PagingData<GithubRepository>>?>(value = null)
    val uiState = combine(
        flow = order,
        flow2 = githubRepositoriesPagingData,
    ) { order, pagingData ->
        HomeScreenUiState.Success(
            order = order,
            githubRepositoriesPagingDataFlow = pagingData,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = HomeScreenUiState.Loading,
    )

    init {
        getGithubRepositoriesPagingData(word = "android")
    }

    fun changeOrder() {
        if (order.value == "desc") {
            order.value = "asc"
        } else if (order.value == "asc") {
            order.value = "desc"
        }
    }

    fun getGithubRepositoriesPagingData(word: String) {
        viewModelScope.launch {
            githubRepositoriesPagingData.value = githubRepositoryRepository
                .getAllGithubRepositories(word = word, order = order.value)
                .cachedIn(viewModelScope)
        }
    }
}
