package com.genesis.testapp.presentation.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.genesis.testapp.R
import com.genesis.testapp.domain.repos.models.Repo
import com.genesis.testapp.presentation.common.observe
import com.genesis.testapp.presentation.common.openLink
import com.genesis.testapp.presentation.common.visible
import com.genesis.testapp.presentation.main.adapter.RepoItem
import com.genesis.testapp.presentation.viewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_history.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

class HistoryFragment : Fragment(), KodeinAware {
    override val kodein: Kodein by kodein()
    private val viewModel: HistoryViewModel by viewModel()

    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyList.adapter = adapter
        observe(viewModel.items, ::reposUpdated)
        observe(viewModel.openRepos, ::openLink)
    }

    private fun onRepoClick(repo: Repo) {
        viewModel.repoClicked(repo)
    }

    private fun reposUpdated(list: List<Repo>) {
        historyEmpty.visible(list.isEmpty())
        historyList.visible(list.isNotEmpty())
        adapter.update(list.map { RepoItem(it, ::onRepoClick) })
    }

    private fun openLink(link: String) {
        context?.openLink(link)
    }
}
