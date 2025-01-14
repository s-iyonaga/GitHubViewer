package com.siyonaga.practice.githubviewer.model

data class GithubRepository(
    val name: String,
    val repositoryPath: String,
    val owner: Owner,
    val stargazersCount: Int,
)
