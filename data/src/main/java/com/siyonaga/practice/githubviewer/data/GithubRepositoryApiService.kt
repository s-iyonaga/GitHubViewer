package com.siyonaga.practice.githubviewer.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubRepositoryApiService {
    @GET("/search/repositories")
    suspend fun getAllGithubRepositories(
        @Query("q") word: String = "android",
        @Query("sort") sort: String = "stars",
        @Query("order") order: String,
        @Query("per_page") perPage: Int,
        @Query("page") nowPage: Int,
    ): GithubRepositories

    @GET("/repos/{owner}/{repo}/readme")
    suspend fun getCurrentGithubRepository(
        @Path("owner") ownerName: String,
        @Path("repo") repoName: String,
    ): GithubRepositoryDetail
}
