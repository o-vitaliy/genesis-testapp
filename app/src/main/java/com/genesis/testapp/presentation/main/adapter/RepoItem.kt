package com.genesis.testapp.presentation.main.adapter

import com.genesis.testapp.R
import com.genesis.testapp.domain.repos.models.Repo
import com.genesis.testapp.presentation.common.visible
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_repos.view.*

data class RepoItem(
    private val repo: Repo,
    private val onClick: (Repo) -> Unit
) : Item() {
    override fun getId(): Long = repo.id
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        with(viewHolder.itemView) {
            itemRepoName.text = repo.name
            itemRepoStar.text = context.getString(R.string.start, repo.stars)
            itemRepoDescription.text = repo.description
            itemRepoDescription.visible(!repo.description.isNullOrBlank())
            itemRepoLanguage.text = repo.language?.let { context.getString(R.string.language, it) }
            itemRepoLanguage.visible(!repo.language.isNullOrBlank())

            setOnClickListener { onClick(repo) }
        }
    }

    override fun getLayout(): Int = R.layout.item_repos
}