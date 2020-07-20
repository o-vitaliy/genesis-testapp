package com.genesis.testapp.domain.repos

import com.genesis.testapp.domain.common.FlowUseCase
import com.genesis.testapp.domain.repos.models.Repo
import kotlinx.coroutines.flow.Flow

class HistoryUseCase(
    private val reposRepository: ReposRepository
) : FlowUseCase<List<Repo>, Unit>() {
    override suspend fun run(params: Unit): Flow<List<Repo>> {
        return reposRepository.history()
    }
}
