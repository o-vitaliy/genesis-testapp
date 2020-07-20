package com.genesis.testapp

import android.app.Application
import com.genesis.testapp.data.dataModule
import com.genesis.testapp.domain.domainModule
import com.genesis.testapp.presentation.viewModelModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import timber.log.Timber

class App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(androidXModule(this@App))
        import(dataModule)
        import(domainModule)
        import(viewModelModule)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
