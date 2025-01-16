package com.siyonaga.practice.githubviewer.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Owner(
    @SerialName("login") val login: String,
    @SerialName("avatarUrl") val avatarUrl: String,
)
