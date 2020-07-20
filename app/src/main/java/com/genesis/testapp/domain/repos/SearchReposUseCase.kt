package com.genesis.testapp.domain.repos

import com.genesis.testapp.domain.common.UseCase
import com.genesis.testapp.domain.repos.models.Repo

class SearchReposUseCase constructor(
    private val reposRepository: ReposRepository
) : UseCase<List<Repo>, SearchReposUseCase.Params>() {

    override suspend fun run(params: Params) = reposRepository.load(
        SearchReposRequest(
            query = params.query,
            offset = params.offset,
            limit = params.limit
        )
    )

    data class Params(val query: String, val offset: Int, val limit: Int)
}