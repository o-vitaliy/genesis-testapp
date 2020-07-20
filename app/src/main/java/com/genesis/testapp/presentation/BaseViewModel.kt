package com.genesis.testapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.genesis.testapp.R
import com.genesis.testapp.data.ResourceProvider
import com.genesis.testapp.domain.common.Result
import com.genesis.testapp.presentation.common.postValue
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException

abstract class BaseViewModel(private val resourceProvider: ResourceProvider) : ViewModel() {
    val error: LiveData<String> = MutableLiveData()


    fun Throwable.traceError() {
        val message = when (this) {
            is IOException, is UnknownHostException -> resourceProvider.getString(R.string.error_no_internet_connection)
            else -> resourceProvider.getString(R.string.error_something_wrong)
        }
        error.postValue(message)
    }

    inline fun <T : Any> Result<T>.doOnError(callback: (Throwable) -> Unit): Result<T> {
        if (this is Result.Error) {
            Timber.e(e)
            callback(e)
        }
        return this
    }

    inline fun <T : Any> Result<T>.doOnSuccess(callback: (T) -> Unit): Result<T> {
        if (this is Result.Success) {
            callback(this.data)
        }
        return this
    }
}