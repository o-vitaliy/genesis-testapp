package com.genesis.testapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.genesis.testapp.R
import com.genesis.testapp.presentation.common.OnNewIntentListener
import com.genesis.testapp.presentation.common.observe
import com.genesis.testapp.presentation.common.openLink
import com.genesis.testapp.presentation.common.showSnackBar
import com.genesis.testapp.presentation.common.visible
import com.genesis.testapp.presentation.viewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

class LoginFragment : Fragment(), KodeinAware, OnNewIntentListener {
    override val kodein: Kodein by kodein()
    private val viewModel: LoginViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.setOnClickListener {
            viewModel.loginClicked()
        }

        observe(viewModel.open, ::openLink)
        observe(viewModel.authorized, ::loggedIn)
        observe(viewModel.error, ::showError)
        observe(viewModel.loading, ::updateLoading)
    }

    private fun updateLoading(loading: Boolean) {
        loginLoading.visible(loading)
    }

    private fun showError(error: String) {
        showSnackBar(error)
    }

    private fun openLink(url: String) {
        context?.openLink(url)
    }

    private fun loggedIn(p: Unit) {
        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
    }

    override fun onNewIntent(data: Intent) {
        viewModel.codeReceived(data.data?.toString())
    }
}
