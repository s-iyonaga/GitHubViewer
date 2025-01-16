package com.siyonaga.practice.githubviewer.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Serializable
data class GithubRepositoryDetail(
    @SerialName("content")
    val encodedReadmeText: String,
) {
    @OptIn(ExperimentalEncodingApi::class)
    val readmeText = Base64.decode(encodedReadmeText).decodeToString()
}
