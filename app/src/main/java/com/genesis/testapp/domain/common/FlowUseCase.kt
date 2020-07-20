package com.genesis.testapp.domain.common

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

abstract class FlowUseCase<out Type, in Params> : CoroutineScope where Type : Any {

    private val job = Job()
    private val uiScope = Dispatchers.Main

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    abstract suspend fun run(params: Params): Flow<Type>

    operator fun invoke(params: Params, onResult: (Result<Flow<Type>>) -> Unit = {}) =
        launch {
            val result = try {
                Result.Success(data = run(params))
            } catch (e: Throwable) {
                Result.Error(e)
            }
            withContext(uiScope) {
                onResult(result)
            }
        }
}
