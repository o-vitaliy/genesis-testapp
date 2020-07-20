package com.genesis.testapp.presentation

import androidx.lifecycle.ViewModelProvider
import com.genesis.testapp.presentation.login.LoginViewModel
import com.genesis.testapp.presentation.main.history.HistoryViewModel
import com.genesis.testapp.presentation.main.search.SearchViewModel
import com.genesis.testapp.presentation.splash.SplashViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val viewModelModule = Kodein.Module(name = "viewModelModule") {
    bind<ViewModelProvider.Factory>() with singleton {
        KodeinViewModelFactory(kodein)
    }

    bind<LoginViewModel>() with singleton { LoginViewModel(instance(), instance()) }
    bind<SplashViewModel>() with singleton { SplashViewModel(instance(), instance()) }
    bind<SearchViewModel>() with singleton { SearchViewModel(instance(), instance(), instance()) }
    bind<HistoryViewModel>() with singleton { HistoryViewModel(instance(), instance()) }
}
