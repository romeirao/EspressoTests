package wanderley.romeiro.italo.espressotests.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import wanderley.romeiro.italo.espressotests.core.EspressoTestsApplication
import wanderley.romeiro.italo.espressotests.core.di.annotations.ApplicationScope

@Module
class ApplicationModule(private val app: EspressoTestsApplication) {

  @Provides
  @ApplicationScope
  fun provideContext(): Context = app
}