package com.siyonaga.practice.githubviewer.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    @SerialName("login")
    val name: String,
    @SerialName("avatar_url")
    val profileIconUrl: String,
)
