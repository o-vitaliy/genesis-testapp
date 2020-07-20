package com.genesis.testapp.domain.repos

import com.genesis.testapp.domain.common.UseCase
import com.genesis.testapp.domain.repos.models.Repo

class SaveHistoryUseCase constructor(
    private val reposRepository: ReposRepository
) : UseCase<Unit, SaveHistoryUseCase.Params>() {

    override suspend fun run(params: Params) = reposRepository.saveIntoHistory(
        SaveHistoryRequest(
            repo = params.repo
        )
    )

    data class Params(val repo: Repo)
}
