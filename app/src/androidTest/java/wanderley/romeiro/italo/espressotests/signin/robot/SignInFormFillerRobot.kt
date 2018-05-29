package wanderley.romeiro.italo.espressotests.signin.robot

import wanderley.romeiro.italo.espressotests.R
import wanderley.romeiro.italo.espressotests.util.robot.BaseFormFillerRobot

class SignInFormFillerRobot: BaseFormFillerRobot() {

    fun fillUsernameInForm(text: String, isReplacement: Boolean = false) = apply {
        typeOnEditText(R.id.usernameEt, text, isReplacement)
    }

    fun fillPasswordInForm(text: String, isReplacement: Boolean = false) = apply {
        typeOnEditText(R.id.passwordEt, text, isReplacement)
    }

    fun hasNoErrorMsg() {
        checkIfThisTextDoesNotExist(R.string.emptyFieldsMsg)
    }

    fun clickToSignIn(): SignInFormFillerResultRobot {
        clickOnButton(R.id.signInBt)
        return SignInFormFillerResultRobot()
    }
}