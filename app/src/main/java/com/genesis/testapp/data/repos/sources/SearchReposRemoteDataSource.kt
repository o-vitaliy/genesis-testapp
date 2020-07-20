package com.genesis.testapp.data.repos.sources

import com.genesis.testapp.data.repos.enities.RepoEntity

class SearchReposRemoteDataSource(
    private val reposApi: ReposApi
) : SearchReposDataSource {
    override suspend fun search(query: String, offset: Int, limit: Int): List<RepoEntity> {
        val result = reposApi.searchRepos(query, offset, limit)
        return result.items
    }
}
