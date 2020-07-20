package com.genesis.testapp.presentation.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.genesis.testapp.data.ResourceProvider
import com.genesis.testapp.domain.repos.HistoryUseCase
import com.genesis.testapp.domain.repos.models.Repo
import com.genesis.testapp.presentation.BaseViewModel
import com.genesis.testapp.presentation.common.LiveEvent
import com.genesis.testapp.presentation.common.postValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryViewModel(
    historyUseCase: HistoryUseCase,
    resourceProvider: ResourceProvider
) : BaseViewModel(resourceProvider) {
    val items: LiveData<List<Repo>> = MutableLiveData()
    val openRepos = LiveEvent<String>()

    init {
        historyUseCase(Unit) {
            it.doOnSuccess { flow ->
                listenHistoryUpdate(flow)
            }
            it.doOnError { e ->
                e.traceError()
            }
        }
    }

    private fun listenHistoryUpdate(flow: Flow<List<Repo>>) {
        viewModelScope.launch {
            flow.collect {
                items.postValue(it)
            }
        }
    }

    fun repoClicked(repo: Repo) {
        openRepos.postValue(repo.url)
    }
}