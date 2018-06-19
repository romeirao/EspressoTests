package wanderley.romeiro.italo.espressotests.signin.di

import dagger.Component
import wanderley.romeiro.italo.espressotests.core.di.ApplicationComponent
import wanderley.romeiro.italo.espressotests.core.di.annotations.ActivityScope
import wanderley.romeiro.italo.espressotests.signin.SignInActivity

@ActivityScope
@Component(modules = [SignInModule::class], dependencies = [ApplicationComponent::class])
interface SignInComponent {
  fun inject(view: SignInActivity)
}