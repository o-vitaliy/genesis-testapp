package com.genesis.testapp.data.network

import com.genesis.testapp.data.auth.sources.AuthApi
import com.genesis.testapp.data.repos.sources.ReposApi
import com.google.gson.Gson
import okhttp3.Interceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.inSet
import org.kodein.di.generic.instance
import org.kodein.di.generic.setBinding
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val apiModule = Kodein.Module("api") {
    bind() from setBinding<Interceptor>()

    bind<Interceptor>().inSet() with singleton { ContentTypeInterceptor() }
    bind<Interceptor>().inSet() with singleton { AuthTokenInterceptor(instance()) }

    bind<Gson>() with singleton { GsonFactory.create() }

    bind<Retrofit>("auth") with singleton {
        RetrofitFactory.create(
            instance(),
            "https://github.com/login/",
            instance()
        )
    }
    bind<Retrofit>("api") with singleton {
        RetrofitFactory.create(
            instance(),
            "https://api.github.com/",
            instance()
        )
    }
    bind<AuthApi>() with singleton { instance<Retrofit>("auth").create(AuthApi::class.java) }
    bind<ReposApi>() with singleton { instance<Retrofit>("api").create(ReposApi::class.java) }
}
