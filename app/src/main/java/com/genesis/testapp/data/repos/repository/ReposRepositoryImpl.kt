package com.genesis.testapp.data.repos.repository

import com.genesis.testapp.data.repos.enities.RepoDbEntity
import com.genesis.testapp.data.repos.enities.RepoEntity
import com.genesis.testapp.data.repos.sources.HistoryDataSource
import com.genesis.testapp.data.repos.sources.SearchReposDataSource
import com.genesis.testapp.domain.repos.ReposRepository
import com.genesis.testapp.domain.repos.SaveHistoryRequest
import com.genesis.testapp.domain.repos.SearchReposRequest
import com.genesis.testapp.domain.repos.models.Repo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReposRepositoryImpl(
    private val searchReposDataSource: SearchReposDataSource,
    private val historyDataSource: HistoryDataSource
) : ReposRepository {
    override suspend fun load(params: SearchReposRequest): List<Repo> {
        val limit = params.limit / 2
        val offset1 = params.offset / limit
        val offset2 = offset1 + 1

        val part1 = GlobalScope.async {
            searchReposDataSource.search(
                query = params.query, offset = offset1, limit = limit
            )
        }
        val part2 = GlobalScope.async {
            searchReposDataSource.search(
                query = params.query, offset = offset2, limit = limit
            )
        }

        return mutableListOf<RepoEntity>().apply {
            addAll(part1.await())
            addAll(part2.await())
        }.map {
            Repo(
                id = it.id,
                name = it.name,
                url = it.url,
                language = it.language,
                description = it.description,
                stars = it.stars
            )
        }
    }

    override suspend fun history(): Flow<List<Repo>> {
        return historyDataSource.getHistory().map { list ->
            list.map {
                Repo(
                    id = it.id,
                    name = it.name,
                    url = it.url,
                    language = it.language,
                    description = it.description,
                    stars = it.score
                )
            }
        }
    }

    override suspend fun saveIntoHistory(request: SaveHistoryRequest) {
        val repo = request.repo
        historyDataSource.saveHistory(
            RepoDbEntity(
                id = repo.id,
                name = repo.name,
                url = repo.url,
                language = repo.language,
                description = repo.description,
                score = repo.stars
            )
        )
    }
}
