package wanderley.romeiro.italo.espressotests.signin.robot

import wanderley.romeiro.italo.espressotests.R
import wanderley.romeiro.italo.espressotests.util.robot.BaseViewAnalyserRobot

class SignInViewAnalyserRobot : BaseViewAnalyserRobot() {

    fun analyseTexts() = apply {
        checkIfThisTextViewIsDisplayed(textViewId = R.id.usernameTv, msgStringId = R.string.username)
    }

    fun analyseFields() = apply {
        checkIfThisTextViewHintIsDisplayed(textViewId = R.id.usernameEt, msgStringId = R.string.usernameHint)
    }

    fun analyseButtons() = apply {
        checkIfThisButtonExists(buttonId = R.id.signInBt, isEnabled = true, msgStringId = R.string.signIn)
    }

    fun analyseImages() = apply {
        checkIfThisImageViewExists(R.id.logo, R.drawable.blizz)
    }
}