package com.siyonaga.practice.githubviewer.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubRepository(
    val name: String,
    // ownerName/repoName の形式の値
    @SerialName("full_name")
    val repositoryPath: String,
    val owner: Owner,
    @SerialName("stargazers_count")
    val stargazersCount: Int,
)
