package com.genesis.testapp.domain.auth

import com.genesis.testapp.domain.common.UseCase

class IsLoggedInUseCase constructor(
    private val authRepository: AuthRepository
) : UseCase<Boolean, Unit>() {

    override suspend fun run(params: Unit) = authRepository.isLoggedIn()
}