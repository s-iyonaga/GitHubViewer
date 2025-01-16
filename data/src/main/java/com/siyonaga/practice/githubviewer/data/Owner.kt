package com.siyonaga.practice.githubviewer.data

import kotlinx.serialization.Serializable

@Serializable
internal data class Owner(
    val login: String,
    val avatarUrl: String,
)
