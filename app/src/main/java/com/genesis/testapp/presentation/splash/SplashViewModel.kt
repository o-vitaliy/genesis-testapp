package com.genesis.testapp.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.genesis.testapp.data.ResourceProvider
import com.genesis.testapp.domain.auth.IsLoggedInUseCase
import com.genesis.testapp.presentation.BaseViewModel
import com.genesis.testapp.presentation.common.postValue

class SplashViewModel(
    resourceProvider: ResourceProvider,
    useCase: IsLoggedInUseCase
) : BaseViewModel(resourceProvider) {

    val authorized: LiveData<Boolean> = MutableLiveData()

    init {
        useCase(Unit) { r ->
            r.doOnSuccess { value ->
                authorized.postValue(value)
            }
            r.doOnError {
                authorized.postValue(false)
            }
        }
    }
}