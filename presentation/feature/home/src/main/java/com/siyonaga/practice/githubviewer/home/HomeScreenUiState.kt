package com.siyonaga.practice.githubviewer.home

import androidx.compose.runtime.Stable

@Stable
data class HomeScreenUiState(
    val order: String = "desc",
)
