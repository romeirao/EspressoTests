package wanderley.romeiro.italo.espressotests.core.di

import android.content.Context
import dagger.Component
import wanderley.romeiro.italo.espressotests.core.di.annotations.ApplicationScope

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun context(): Context
}