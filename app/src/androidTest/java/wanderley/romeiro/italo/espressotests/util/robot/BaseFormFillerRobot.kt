package wanderley.romeiro.italo.espressotests.util.robot

import wanderley.romeiro.italo.espressotests.util.robot.extension.replaceText
import wanderley.romeiro.italo.espressotests.util.robot.extension.typeText

abstract class BaseFormFillerRobot : BaseViewAnalyserRobot() {

    fun typeOnEditText(editTextId: Int, content: String, canReplace: Boolean, needToScroll: Boolean = false) {
        if (canReplace) {
            withThisViewId(editTextId).replaceText(content, needToScroll)
        } else {
            withThisViewId(editTextId).typeText(content, needToScroll)
        }
    }
}