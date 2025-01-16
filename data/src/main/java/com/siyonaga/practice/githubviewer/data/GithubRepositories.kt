package com.siyonaga.practice.githubviewer.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubRepositories(
    @SerialName("items")
    val githubRepository: List<GithubRepository>,
)
