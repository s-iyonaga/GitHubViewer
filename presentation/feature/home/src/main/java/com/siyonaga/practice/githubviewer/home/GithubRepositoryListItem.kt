package com.siyonaga.practice.githubviewer.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.siyonaga.practice.githubviewer.model.GithubRepository

@Composable
fun GithubRepositoryListItem(
    modifier: Modifier = Modifier,
    githubRepository: GithubRepository,
) {
    Column(modifier = modifier) {
        Text(text = githubRepository.repositoryPath)
        Text(text = githubRepository.stargazersCount.toString())
    }
}
