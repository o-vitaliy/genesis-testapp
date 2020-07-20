package com.genesis.testapp.presentation.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.genesis.testapp.data.ResourceProvider
import com.genesis.testapp.domain.repos.SaveHistoryUseCase
import com.genesis.testapp.domain.repos.SearchReposUseCase
import com.genesis.testapp.domain.repos.models.Repo
import com.genesis.testapp.presentation.BaseViewModel
import com.genesis.testapp.presentation.common.LiveEvent
import com.genesis.testapp.presentation.common.postValue
import kotlinx.coroutines.Job

class SearchViewModel(
    private val searchReposUseCase: SearchReposUseCase,
    private val saveHistoryUseCase: SaveHistoryUseCase,
    resourceProvider: ResourceProvider
) : BaseViewModel(resourceProvider) {

    val repos: LiveData<List<Repo>> = MutableLiveData()
    val openRepos = LiveEvent<String>()

    private var query: String? = null
    private var loadingJob: Job? = null

    fun onQueryChanged(query: String?) {
        repos.postValue(emptyList())
        this.query = query
        loadingJob?.cancel()
        if (!query.isNullOrBlank()) {
            loadPage()
        }
    }

    fun loadNext() {
        loadingJob?.cancel()
        loadPage()
    }

    private fun loadPage() {
        val q = query ?: return
        val oldRepos = repos.value ?: emptyList()
        val offset = oldRepos.size
        if (offset % LIMIT == 0) {
            loadingJob = searchReposUseCase(
                SearchReposUseCase.Params(q, offset, LIMIT)
            ) { r ->
                r.doOnSuccess { newRepos ->
                    val all = mutableListOf<Repo>().apply {
                        addAll(oldRepos)
                        addAll(newRepos)
                    }
                    repos.postValue(all)
                }
                r.doOnError {
                    it.traceError()
                }
            }
        }
    }

    fun repoClicked(repo: Repo) {
        saveHistoryUseCase(SaveHistoryUseCase.Params(repo)) {
            it.doOnError { e -> e.traceError() }
        }
        openRepos.postValue(repo.url)
    }

    private companion object {
        const val LIMIT = 30
    }
}
