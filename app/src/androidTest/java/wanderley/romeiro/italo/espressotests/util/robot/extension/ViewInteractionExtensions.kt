package wanderley.romeiro.italo.espressotests.util.robot.extension

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsNot.not

// GENERAL VIEW
fun ViewInteraction.checkViewIsDisplayed(): ViewInteraction =
    check(
        matches(
            isDisplayed()
        )
    )

fun ViewInteraction.checkViewIsNotDisplayed(): ViewInteraction =
    check(
        matches(
            not(isDisplayed())
        )
    )

fun ViewInteraction.checkViewDoesNotExist(): ViewInteraction =
    check(
        doesNotExist()
    )

// TEXT
fun ViewInteraction.checkText(msgStringId: Int, vararg extraContent: String, isHint: Boolean = false): ViewInteraction =
    check(
        matches(
            allOf(
                isDisplayed(),
                withTextAndParameters(resourceId = msgStringId, extraContent = *extraContent, isHint = isHint)
            )
        )
    )

// KEYBOARD
fun ViewInteraction.dismissKeyboard(isScrolling: Boolean): ViewInteraction = when {
    isScrolling -> perform(closeSoftKeyboard(), scrollTo())
    else -> perform(closeSoftKeyboard())
}

// IMAGE
fun ViewInteraction.checkImageData(drawableId: Int): ViewInteraction =
    check(
        matches(
            allOf(
                isDisplayed(),
                withDrawable(drawableId)
            )
        )
    )

// EDIT TEXT
fun ViewInteraction.typeText(text: String, isScrolling: Boolean = false): ViewInteraction = when {
    isScrolling -> perform(closeSoftKeyboard(), scrollTo(), ViewActions.typeText(text), closeSoftKeyboard())
    else -> perform(closeSoftKeyboard(), ViewActions.typeText(text), closeSoftKeyboard())
}

fun ViewInteraction.replaceText(text: String, isScrolling: Boolean = false): ViewInteraction = when {
    isScrolling -> perform(closeSoftKeyboard(), scrollTo(), ViewActions.replaceText(text), closeSoftKeyboard())
    else -> perform(closeSoftKeyboard(), ViewActions.replaceText(text), closeSoftKeyboard())
}

// BUTTON
fun ViewInteraction.checkButtonData(isEnabled: Boolean, msgStringId: Int, vararg extraContent: String): ViewInteraction = check(matches(allOf(isDisplayed(), when {
    isEnabled -> isEnabled()
    else -> not(isEnabled())
}, withTextAndParameters(resourceId = msgStringId, extraContent = *extraContent))))

fun ViewInteraction.clickOnButton(isScrolling: Boolean = false): ViewInteraction = when {
    isScrolling -> perform(closeSoftKeyboard(), scrollTo(), click())
    else -> perform(closeSoftKeyboard(), click())
}