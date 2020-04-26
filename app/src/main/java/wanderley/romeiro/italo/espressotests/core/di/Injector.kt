package wanderley.romeiro.italo.espressotests.core.di

import wanderley.romeiro.italo.espressotests.core.EspressoTestsApplication

class Injector private constructor() {
    companion object {
        fun get(): ApplicationComponent = EspressoTestsApplication.INSTANCE.component
    }
}