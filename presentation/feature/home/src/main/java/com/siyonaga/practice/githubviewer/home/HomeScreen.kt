package com.siyonaga.practice.githubviewer.home

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val lazyPagingItems = viewModel.githubRepositoriesPagingData?.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeScreenTopAppBar(
                order = uiState.order,
                onSortButtonClicked = {
                    viewModel.changeOrder()
                },
            )
        },
    ) { contentPadding ->
        if (lazyPagingItems != null) {
            GithubRepositoryList(
                lazyPagingItems = lazyPagingItems,
                contentPadding = contentPadding,
            )
        } else {
            Text(text = "No List Items.")
        }
    }
}
