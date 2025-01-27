package com.siyonaga.practice.githubviewer.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val lazyPagingItems = viewModel.githubRepositoriesPagingData.collectAsLazyPagingItems()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HomeScreenTopAppBar(
            word = uiState.word,
            order = uiState.order,
            onSearchClicked = { word ->
                viewModel.searchGithubRepositories(word)
            },
            onSortButtonClicked = {
                viewModel.changeOrder()
            },
        )

        Spacer(modifier = Modifier.height(16.dp))
        when (lazyPagingItems.loadState.refresh) {
            is LoadState.NotLoading -> {
                GithubRepositoryList(
                    lazyPagingItems = lazyPagingItems,
                )
            }
            is LoadState.Loading -> {
                Box(
                    modifier = Modifier.weight(1.0f),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
            is LoadState.Error -> {
                // TODO: （現時点ではここに辿り着けない）
                Column(
                    modifier = Modifier.weight(1.0f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Warning,
                        contentDescription = stringResource(R.string.connection_error),
                        modifier = Modifier.size(40.dp),
                        tint = Color.Red,
                    )
                    Text(
                        text = stringResource(R.string.connection_error),
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )
                }
            }
        }
    }
}
