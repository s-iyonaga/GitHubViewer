package com.siyonaga.practice.githubviewer.data

import retrofit2.http.GET

interface GithubRepositoryApiService {
    @GET("search/repositories?q=Android&sort=stars&order=[asc or desc]")
    suspend fun getAllGithubRepositories(): GithubRepositories

    @GET("repos/[ownerName]/[repoName]/readme")
    suspend fun getCurrentGithubRepository(): GithubRepositoryDetail
}
