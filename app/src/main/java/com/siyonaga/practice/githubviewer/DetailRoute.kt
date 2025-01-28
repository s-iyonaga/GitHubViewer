package com.siyonaga.practice.githubviewer

import kotlinx.serialization.Serializable

@Serializable
data class DetailRoute(
    val ownerName: String,
    val repoName: String,
)
