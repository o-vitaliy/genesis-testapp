package com.genesis.testapp.data.repos.sources

import com.genesis.testapp.data.repos.enities.RepoDbEntity
import kotlinx.coroutines.flow.Flow

interface HistoryDataSource {
    suspend fun getHistory(): Flow<List<RepoDbEntity>>
    suspend fun saveHistory(repo: RepoDbEntity)
}
