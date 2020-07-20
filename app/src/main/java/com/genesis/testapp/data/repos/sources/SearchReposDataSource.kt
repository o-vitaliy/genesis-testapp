package com.genesis.testapp.data.repos.sources

import com.genesis.testapp.data.repos.enities.RepoEntity

interface SearchReposDataSource {
    suspend fun search(query: String, offset: Int, limit: Int): List<RepoEntity>
}
