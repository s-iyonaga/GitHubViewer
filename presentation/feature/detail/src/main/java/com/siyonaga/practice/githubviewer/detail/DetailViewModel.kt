package com.siyonaga.practice.githubviewer.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siyonaga.practice.githubviewer.repository.GithubRepositoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val githubRepositoryRepository: GithubRepositoryRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<DetailScreenUiState> =
        MutableStateFlow(DetailScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getGithubRepositoryDetail(ownerName: String, repoName: String) {
        viewModelScope.launch {
            _uiState.value = runCatching {
                githubRepositoryRepository.getCurrentGithubRepository(
                    ownerName = ownerName,
                    repoName = repoName,
                )
            }.fold(
                onSuccess = {
                    DetailScreenUiState.Success(it.readmeText)
                },
                onFailure = {
                    DetailScreenUiState.Error
                },
            )
        }
    }
}
