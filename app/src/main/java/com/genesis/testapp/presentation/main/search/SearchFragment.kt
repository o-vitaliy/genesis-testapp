package com.genesis.testapp.presentation.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.genesis.testapp.R
import com.genesis.testapp.domain.repos.models.Repo
import com.genesis.testapp.presentation.common.EndlessRecyclerOnScrollListener
import com.genesis.testapp.presentation.common.observe
import com.genesis.testapp.presentation.common.openLink
import com.genesis.testapp.presentation.common.showSnackBar
import com.genesis.testapp.presentation.main.adapter.RepoItem
import com.genesis.testapp.presentation.viewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_search.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

class SearchFragment : Fragment(), KodeinAware {
    override val kodein: Kodein by kodein()
    private val viewModel: SearchViewModel by viewModel()

    private var scrollEndListener: EndlessRecyclerOnScrollListener? = null
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchField()
        initResultList()
        observe(viewModel.repos, ::reposUpdated)
        observe(viewModel.error, ::showError)
        observe(viewModel.openRepos, ::openLink)
    }

    private fun openLink(link: String) {
        context?.openLink(link)
    }

    private fun showError(error: String) {
        showSnackBar(error)
    }

    private fun initSearchField() {
        with(searchInput) {
            addTextChangedListener {
                viewModel.onQueryChanged(it?.toString() ?: "")
            }
            setOnEditorActionListener { v, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.onQueryChanged(v.text?.toString() ?: "")
                    true
                } else {
                    false
                }
            }
        }
    }

    private fun initResultList() {
        searchResultList.adapter = adapter
        val layoutManager = searchResultList.layoutManager as LinearLayoutManager
        scrollEndListener = object : EndlessRecyclerOnScrollListener(layoutManager) {
            override fun onLoadMore(currentPage: Int) = viewModel.loadNext()
        }
    }

    private fun onRepoClick(repo: Repo) {
        viewModel.repoClicked(repo)
    }

    private fun reposUpdated(list: List<Repo>) {
        scrollEndListener?.let { searchResultList.removeOnScrollListener(it) }
        val mapped = list.map { RepoItem(it, ::onRepoClick) }
        adapter.update(mapped)
        scrollEndListener?.let { searchResultList.addOnScrollListener(it) }
    }
}
