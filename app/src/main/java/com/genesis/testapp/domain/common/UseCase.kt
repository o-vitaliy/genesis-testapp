package com.genesis.testapp.domain.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class UseCase<out Type, in Params> : CoroutineScope where Type : Any {

    private val job = Job()
    private val uiScope = Dispatchers.Main

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    abstract suspend fun run(params: Params): Type

    @SuppressWarnings("TooGenericExceptionCaught")
    operator fun invoke(params: Params, onResult: (Result<Type>) -> Unit = {}) =
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
