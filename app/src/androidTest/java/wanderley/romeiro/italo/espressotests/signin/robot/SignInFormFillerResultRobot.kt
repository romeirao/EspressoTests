package wanderley.romeiro.italo.espressotests.signin.robot

import wanderley.romeiro.italo.espressotests.R
import wanderley.romeiro.italo.espressotests.util.robot.BaseViewAnalyserRobot

class SignInFormFillerResultRobot : BaseViewAnalyserRobot() {

    fun reachDashboardView() = apply {
        checkIfThisViewIsDisplayed(R.id.main)
    }

    fun hasEmptyFieldErrorMsg() = apply {
        checkIfTextIsDisplayed(R.string.emptyFieldsMsg)
    }
}