package com.siyonaga.practice.githubviewer.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Serializable
internal data class GithubRepositoryDetail(
    @SerialName("content") val content: String,
)

@OptIn(ExperimentalEncodingApi::class)
internal val GithubRepositoryDetail.readmeText get() = Base64.decode(content).decodeToString()
