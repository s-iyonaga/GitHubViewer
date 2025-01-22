package com.siyonaga.practice.githubviewer.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.siyonaga.practice.githubviewer.model.GithubRepository

@Composable
fun GithubRepositoryList(
    modifier: Modifier = Modifier,
    lazyPagingItems: LazyPagingItems<GithubRepository>,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey(),
        ) { index ->
            val githubRepository = lazyPagingItems[index]

            if (githubRepository != null) {
                GithubRepositoryListItem(githubRepository = githubRepository)
            } else {
                Text(text = "No List Item.")
            }
        }
    }
}
