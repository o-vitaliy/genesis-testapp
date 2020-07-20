package com.genesis.testapp.domain.auth

import com.genesis.testapp.domain.common.UseCase

class LoginUseCase constructor(
    private val authRepository: AuthRepository
) : UseCase<Unit, LoginUseCase.Params>() {

    override suspend fun run(params: Params) = authRepository.login(
        LoginRequest(
            code = params.code
        )
    )

    data class Params(val code: String)
}
