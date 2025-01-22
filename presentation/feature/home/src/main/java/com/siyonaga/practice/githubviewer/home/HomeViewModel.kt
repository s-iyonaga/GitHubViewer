package com.siyonaga.practice.githubviewer.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.siyonaga.practice.githubviewer.model.GithubRepository
import com.siyonaga.practice.githubviewer.model.Order
import com.siyonaga.practice.githubviewer.repository.GithubRepositoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val githubRepositoryRepository: GithubRepositoryRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()
    private var githubRepositoriesPagingData: Flow<PagingData<GithubRepository>>? = null

    init {
        getGithubRepositoriesPagingData(word = "android")
    }

    fun changeOrder() {
        _uiState.update { state ->
            state.copy(
                order = if (_uiState.value.order == Order.DESC) Order.ASC else Order.DESC,
            )
        }
    }

    fun getGithubRepositoriesPagingData(word: String) {
        viewModelScope.launch {
            githubRepositoriesPagingData = runCatching {
                githubRepositoryRepository.getAllGithubRepositories(
                    word = word,
                    order = _uiState.value.order.orderName,
                )
            }.fold(
                onSuccess = {
                    it.cachedIn(viewModelScope)
                },
                onFailure = {
                    null
                },
            )
        }
    }
}
