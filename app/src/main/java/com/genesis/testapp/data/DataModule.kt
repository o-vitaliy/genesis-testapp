package com.genesis.testapp.data

import android.content.SharedPreferences
import com.genesis.testapp.data.api.apiModule
import com.genesis.testapp.data.auth.repository.AuthRepositoryImpl
import com.genesis.testapp.data.auth.sources.AuthDataSource
import com.genesis.testapp.data.auth.sources.AuthRemoteDataSource
import com.genesis.testapp.data.auth.sources.TokenDataSource
import com.genesis.testapp.data.auth.sources.TokenLocalDataSource
import com.genesis.testapp.data.prefs.PrefsSourceFactory
import com.genesis.testapp.domain.auth.AuthRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val dataModule = Kodein.Module("data") {
    import(apiModule)
    bind<SharedPreferences>() with singleton {
        PrefsSourceFactory.create(instance())
    }

    bind<ResourceProvider>() with singleton {
        ResourceProvider(instance())
    }

    bind<TokenDataSource>() with singleton {
        TokenLocalDataSource(instance())
    }
    bind<AuthDataSource>() with singleton {
        AuthRemoteDataSource(
            instance(),
            instance()
        )
    }
    bind<AuthRepository>() with singleton {
        AuthRepositoryImpl(instance(), instance())
    }
}
