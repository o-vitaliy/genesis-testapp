package com.genesis.testapp.domain.repos

import com.genesis.testapp.domain.repos.models.Repo
import kotlinx.coroutines.flow.Flow

interface ReposRepository {

    suspend fun load(params: SearchReposRequest): List<Repo>

    suspend fun history(): Flow<List<Repo>>

    suspend fun saveIntoHistory(saveHistoryRequest: SaveHistoryRequest)
}