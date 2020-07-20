package com.genesis.testapp.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.genesis.testapp.R
import com.genesis.testapp.presentation.common.observe
import com.genesis.testapp.presentation.viewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

class SplashFragment : Fragment(), KodeinAware {
    override val kodein: Kodein by kodein()
    private val viewModel: SplashViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_spash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.authorized, ::authorized)
    }

    private fun authorized(value: Boolean) {
        if (value) {
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        } else {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }

}