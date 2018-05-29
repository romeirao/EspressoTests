package wanderley.romeiro.italo.espressotests.util.robot

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers.withId
import wanderley.romeiro.italo.espressotests.util.robot.extension.checkButtonData
import wanderley.romeiro.italo.espressotests.util.robot.extension.checkImageData
import wanderley.romeiro.italo.espressotests.util.robot.extension.checkText
import wanderley.romeiro.italo.espressotests.util.robot.extension.checkViewDoesNotExist
import wanderley.romeiro.italo.espressotests.util.robot.extension.checkViewIsDisplayed
import wanderley.romeiro.italo.espressotests.util.robot.extension.checkViewIsNotDisplayed
import wanderley.romeiro.italo.espressotests.util.robot.extension.clickOnButton
import wanderley.romeiro.italo.espressotests.util.robot.extension.dismissKeyboard
import wanderley.romeiro.italo.espressotests.util.robot.extension.withTextAndParameters

abstract class BaseViewAnalyserRobot {

    //BUTTON
    fun checkIfThisButtonExists(buttonId: Int, isEnabled: Boolean, msgStringId: Int, vararg extraContent: String, needToScroll: Boolean = false) =
        withThisViewId(buttonId)
            .dismissKeyboard(needToScroll)
            .checkButtonData(isEnabled, msgStringId, *extraContent)

    fun clickOnButton(buttonId: Int) =
        withThisViewId(buttonId)
            .clickOnButton()

    // IMAGE VIEW
    fun checkIfThisImageViewExists(imageViewId: Int, drawableId: Int, needToScroll: Boolean = false) =
        withThisViewId(imageViewId)
            .dismissKeyboard(needToScroll)
            .checkImageData(drawableId)

    // TEXT VIEW
    fun checkIfThisTextViewIsDisplayed(textViewId: Int, msgStringId: Int, vararg extraContent: String, needToScroll: Boolean = false): ViewInteraction =
        withThisViewId(textViewId)
            .dismissKeyboard(needToScroll)
            .checkText(msgStringId, *extraContent)

    fun checkIfThisTextViewHintIsDisplayed(textViewId: Int, msgStringId: Int, vararg extraContent: String, needToScroll: Boolean = false): ViewInteraction =
        withThisViewId(textViewId)
            .dismissKeyboard(needToScroll)
            .checkText(msgStringId, *extraContent, isHint = true)

    // ONLY TEXTs
    fun checkIfTextIsDisplayed(stringId: Int, vararg extraContent: String, needToScroll: Boolean = false) =
        withThisStringId(stringId, *extraContent)
            .dismissKeyboard(needToScroll)
            .checkViewIsDisplayed()

    fun checkIfTextIsNotDisplayed(stringId: Int, vararg extraContent: String) =
        withThisStringId(stringId, *extraContent)
            .checkViewIsNotDisplayed()

    fun checkIfThisTextDoesNotExist(stringId: Int, vararg extraContent: String) =
        withThisStringId(stringId, *extraContent)
            .checkViewDoesNotExist()

    // GENERAL VIEW
    fun checkIfThisViewIsDisplayed(viewId: Int, needToScroll: Boolean = false) =
        withThisViewId(viewId)
            .dismissKeyboard(needToScroll)
            .checkViewIsDisplayed()

    fun checkIfThisViewIsNotDisplayed(viewId: Int) =
        withThisViewId(viewId)
            .checkViewIsNotDisplayed()

    fun checkIfThisViewDoesNotExist(viewId: Int) =
        withThisViewId(viewId)
            .checkViewDoesNotExist()

    // GENERAL HELPERS
    fun withThisViewId(viewId: Int): ViewInteraction = onView(withId(viewId))

    fun withThisStringId(stringId: Int, vararg extraContent: String): ViewInteraction = onView(withTextAndParameters(stringId, *extraContent))
}