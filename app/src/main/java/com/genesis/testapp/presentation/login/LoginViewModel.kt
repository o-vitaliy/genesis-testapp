package com.genesis.testapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.genesis.testapp.R
import com.genesis.testapp.data.ResourceProvider
import com.genesis.testapp.domain.LinkHelper
import com.genesis.testapp.domain.auth.LoginUseCase
import com.genesis.testapp.presentation.BaseViewModel
import com.genesis.testapp.presentation.common.LiveEvent
import com.genesis.testapp.presentation.common.postValue

class LoginViewModel(
    private val resourceProvider: ResourceProvider,
    private val loginUseCase: LoginUseCase
) : BaseViewModel(resourceProvider) {

    val open: LiveData<String> = MutableLiveData()
    val authorized = LiveEvent<Unit>()

    fun loginClicked() {
        val clientId = resourceProvider.getString(R.string.github_client_id)
        open.postValue(resourceProvider.getString(R.string.github_auth_link, clientId))
    }

    fun codeReceived(url: String?) {
        val code = LinkHelper.getParam(url, CODE) ?: return

        loginUseCase(
            LoginUseCase.Params(code = code)
        ) {
            it.doOnSuccess {
                authorized.postValue(Unit)
            }
            it.doOnError { t ->
                t.traceError()
            }
        }
    }

    private companion object {
        const val CODE = "code"
    }
}