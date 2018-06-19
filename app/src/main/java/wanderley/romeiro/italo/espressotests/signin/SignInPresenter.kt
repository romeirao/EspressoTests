package wanderley.romeiro.italo.espressotests.signin

import android.content.Context
import wanderley.romeiro.italo.espressotests.core.di.annotations.ActivityScope
import javax.inject.Inject

@ActivityScope
class SignInPresenter @Inject constructor(private val view: SignInView) {

  @Inject
  lateinit var context: Context

  var username: String = ""
  var password: String = ""

  fun clickOnSignIn() {
    when {
      hasValidFields() -> {
        view.clearErrorMsg()
        view.goToDashboard()
      }
      else -> view.emptyFieldErrorMsg()
    }
  }

  private fun hasValidFields() = username.isNotBlank() && password.isNotBlank()
}