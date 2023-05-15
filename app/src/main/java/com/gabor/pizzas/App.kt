package com.gabor.pizzas

import android.app.Application
import com.gabor.pizzas.di.dataModule
import com.gabor.pizzas.di.networkModule
import com.gabor.pizzas.di.useCaseModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                dataModule,
                useCaseModule
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}