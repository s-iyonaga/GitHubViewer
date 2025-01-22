package com.siyonaga.practice.githubviewer.home

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
data class HomeScreenUiState(
    val order: Order = Order.DESC,
)

@Immutable
enum class Order(val orderName: String) {
    ASC(orderName = "asc"),
    DESC(orderName = "desc"),
}
