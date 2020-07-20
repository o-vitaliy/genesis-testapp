package com.genesis.testapp.data.repos.sources

import com.genesis.testapp.data.repos.dao.RepoDao
import com.genesis.testapp.data.repos.enities.RepoDbEntity
import kotlinx.coroutines.flow.Flow

class HistoryDbDataSource(
    private val repoDao: RepoDao
) : HistoryDataSource {
    override suspend fun getHistory(): Flow<List<RepoDbEntity>> {
        return repoDao.getAll()
    }

    override suspend fun saveHistory(repo: RepoDbEntity) {
        repoDao.insert(repo)
    }
}
