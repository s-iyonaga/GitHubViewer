package com.siyonaga.practice.githubviewer.data

import kotlinx.serialization.Serializable

@Serializable
internal data class GithubRepositories(
    val items: List<GithubRepository>,
)
