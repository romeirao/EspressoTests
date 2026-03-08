package wanderley.romeiro.italo.espressotests.core

import android.app.Application
import timber.log.Timber
import wanderley.romeiro.italo.espressotests.BuildConfig
import wanderley.romeiro.italo.espressotests.core.di.ApplicationComponent
import wanderley.romeiro.italo.espressotests.core.di.ApplicationModule
import wanderley.romeiro.italo.espressotests.core.di.DaggerApplicationComponent

class EspressoTestsApplication : Application() {

    companion object {
        lateinit var INSTANCE: EspressoTestsApplication
            private set
    }

    lateinit var component: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        setupTimber()
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}