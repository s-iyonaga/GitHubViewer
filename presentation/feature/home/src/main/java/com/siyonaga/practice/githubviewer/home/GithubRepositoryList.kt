package com.siyonaga.practice.githubviewer.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.siyonaga.practice.githubviewer.model.GithubRepository

@Composable
internal fun GithubRepositoryList(
    lazyPagingItems: LazyPagingItems<GithubRepository>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
    ) {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey(),
        ) { index ->
            val githubRepository = lazyPagingItems[index]

            githubRepository?.let {
                GithubRepositoryListItem(githubRepository = githubRepository)
            }
        }
    }
}
