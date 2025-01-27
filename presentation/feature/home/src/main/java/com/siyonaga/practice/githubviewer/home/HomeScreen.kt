package com.siyonaga.practice.githubviewer.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    // TODO: ホーム画面：未完成
    val lazyPagingItems = viewModel.githubRepositoriesPagingData?.collectAsLazyPagingItems()

    if (lazyPagingItems != null) {
        GithubRepositoryList(lazyPagingItems = lazyPagingItems)
    } else {
        Text(text = "No List Items.")
    }
}
