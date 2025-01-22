package com.siyonaga.practice.githubviewer.home

import androidx.compose.runtime.Stable
import com.siyonaga.practice.githubviewer.repository.Order

@Stable
data class HomeScreenUiState(
    val order: Order = Order.DESC,
)
