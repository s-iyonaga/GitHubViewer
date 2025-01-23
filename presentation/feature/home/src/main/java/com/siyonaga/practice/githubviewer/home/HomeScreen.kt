package com.siyonaga.practice.githubviewer.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    // TODO: ホーム画面：未完成
    val lazyPagingItems = viewModel.githubRepositoriesPagingData?.collectAsLazyPagingItems()

    if (lazyPagingItems != null) {
        GithubRepositoryList(
            lazyPagingItems = lazyPagingItems,
            modifier = modifier,
        )
    } else {
        Text(text = "No List Items.")
    }
}
