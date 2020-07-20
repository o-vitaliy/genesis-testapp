package com.genesis.testapp.data.repos.sources

import com.genesis.testapp.data.repos.responses.SearchRepoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ReposApi {
    @GET("search/repositories")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("page") offset: Int,
        @Query("per_page") limit: Int,
        @Query("sort") sort: String = "stars"
    ): SearchRepoResponse
}