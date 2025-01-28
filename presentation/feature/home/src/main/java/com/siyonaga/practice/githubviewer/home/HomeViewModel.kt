package com.siyonaga.practice.githubviewer.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.siyonaga.practice.githubviewer.model.Order
import com.siyonaga.practice.githubviewer.repository.GithubRepositoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val githubRepositoryRepository: GithubRepositoryRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenUiState.Success())
    val uiState = _uiState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val pagingData = uiState.flatMapLatest {
        githubRepositoryRepository.getAllGithubRepositories(
            word = "android",
            order = it.order,
        )
    }.cachedIn(viewModelScope)

    fun searchGithubRepositories(word: String) {
        _uiState.update { state ->
            state.copy(
                word = word,
            )
        }
    }

    fun changeOrder() {
        _uiState.update { state ->
            state.copy(
                order = if (_uiState.value.order == Order.DESC) Order.ASC else Order.DESC,
            )
        }
    }
}
