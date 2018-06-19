package wanderley.romeiro.italo.espressotests.signin.di

import dagger.Module
import dagger.Provides
import wanderley.romeiro.italo.espressotests.core.di.annotations.ActivityScope
import wanderley.romeiro.italo.espressotests.signin.SignInView

@Module
class SignInModule(private val signInView: SignInView) {

  @Provides
  @ActivityScope
  fun provideSignInView() = signInView
}