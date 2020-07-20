package com.genesis.testapp.presentation.common

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.*

class TabAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {
    private val fragmentsClasses = ArrayList<Class<out Fragment>>()
    private val titles = ArrayList<String>()

    fun addFragment(fragment: Class<out Fragment>, title: String) {
        fragmentsClasses.add(fragment)
        titles.add(title)
    }

    fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }


    override fun getItemCount(): Int = fragmentsClasses.size

    override fun createFragment(position: Int): Fragment {
        return fragmentsClasses[position].newInstance()
    }
}