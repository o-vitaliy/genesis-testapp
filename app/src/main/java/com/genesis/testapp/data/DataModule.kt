package com.genesis.testapp.data

import android.content.SharedPreferences
import com.genesis.testapp.data.auth.repository.AuthRepositoryImpl
import com.genesis.testapp.data.auth.sources.AuthDataSource
import com.genesis.testapp.data.auth.sources.AuthRemoteDataSource
import com.genesis.testapp.data.auth.sources.TokenDataSource
import com.genesis.testapp.data.auth.sources.TokenLocalDataSource
import com.genesis.testapp.data.db.AppDatabase
import com.genesis.testapp.data.db.DataBaseFactory
import com.genesis.testapp.data.network.apiModule
import com.genesis.testapp.data.prefs.PrefsSourceFactory
import com.genesis.testapp.data.repos.dao.RepoDao
import com.genesis.testapp.data.repos.repository.ReposRepositoryImpl
import com.genesis.testapp.data.repos.sources.HistoryDataSource
import com.genesis.testapp.data.repos.sources.HistoryDbDataSource
import com.genesis.testapp.data.repos.sources.SearchReposDataSource
import com.genesis.testapp.data.repos.sources.SearchReposRemoteDataSource
import com.genesis.testapp.domain.auth.AuthRepository
import com.genesis.testapp.domain.repos.ReposRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val dataModule = Kodein.Module("data") {
    import(apiModule)
    bind<SharedPreferences>() with singleton {
        PrefsSourceFactory.create(instance())
    }
    bind<AppDatabase>() with singleton {
        DataBaseFactory.create(instance())
    }
    bind<RepoDao>() with singleton {
        instance<AppDatabase>().repoDao()
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

    bind<SearchReposDataSource>() with singleton {
        SearchReposRemoteDataSource(instance())
    }

    bind<ReposRepository>() with singleton {
        ReposRepositoryImpl(instance(), instance())
    }
    bind<HistoryDataSource>() with singleton {
        HistoryDbDataSource(instance())
    }

}
