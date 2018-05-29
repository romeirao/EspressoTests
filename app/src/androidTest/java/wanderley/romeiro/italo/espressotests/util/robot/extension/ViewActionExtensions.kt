package wanderley.romeiro.italo.espressotests.util.robot.extension

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.view.View
import android.widget.EditText
import org.hamcrest.Matcher

fun ViewActions.changeEditTextInputType(vararg inputTypes: Int) = object: ViewAction {

    override fun getConstraints(): Matcher<View> = isAssignableFrom(EditText::class.java)

    override fun getDescription(): String = "---"

    override fun perform(uiController: UiController, view: View) {
        val editText = view as EditText
        var i = 0
        var inputTypeResult = inputTypes[i++]
        while (i < inputTypes.size) {
            inputTypeResult = inputTypeResult or inputTypes[i++]
        }
        editText.inputType = inputTypeResult
    }
}