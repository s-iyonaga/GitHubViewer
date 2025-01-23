package com.siyonaga.practice.githubviewer.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.siyonaga.practice.githubviewer.model.GithubRepository

@Composable
internal fun GithubRepositoryListItem(
    githubRepository: GithubRepository,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        // TODO: AsyncImage()
        Column {
            Text(text = githubRepository.name)
            Row {
                // TODO: Icon()
                Text(text = githubRepository.stargazersCount.toString())
            }
        }
    }
}
