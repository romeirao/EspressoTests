package wanderley.romeiro.italo.espressotests.core.api

import wanderley.romeiro.italo.espressotests.core.di.annotations.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class NetworkApi @Inject constructor() {

    fun validateUser(username: String, password: String): Boolean {
        return !username.isEmpty()
    }
}