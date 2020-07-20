package com.genesis.testapp.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.genesis.testapp.R
import com.genesis.testapp.presentation.common.TabAdapter
import com.genesis.testapp.presentation.login.LoginViewModel
import com.genesis.testapp.presentation.main.history.HistoryFragment
import com.genesis.testapp.presentation.main.search.SearchFragment
import com.genesis.testapp.presentation.viewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

class MainFragment : Fragment(), KodeinAware {
    override val kodein: Kodein by kodein()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TabAdapter(this).apply {
            addFragment(SearchFragment::class.java, getString(R.string.search))
            addFragment(HistoryFragment::class.java, getString(R.string.history))
        }
        mainViewPager.adapter = adapter

        TabLayoutMediator(mainTabLayout, mainViewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }
}