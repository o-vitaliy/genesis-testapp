package com.genesis.testapp.domain

import com.genesis.testapp.domain.auth.IsLoggedInUseCase
import com.genesis.testapp.domain.auth.LoginUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val domainModule = Kodein.Module("domain") {

    bind<LoginUseCase>() with singleton {
        LoginUseCase(instance())
    }
    bind<IsLoggedInUseCase>() with singleton {
        IsLoggedInUseCase(instance())
    }
}
