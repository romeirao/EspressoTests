package wanderley.romeiro.italo.espressotests.util.robot.extension

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import wanderley.romeiro.italo.espressotests.util.logError

fun withDrawable(expectedDrawableId: Int) = object: TypeSafeMatcher<View>() {
    private var resourceName: String? = null

    override fun matchesSafely(target: View): Boolean {
        if (target !is ImageView) return false

        val resources = target.context.resources
        try {
            resourceName = resources.getResourceEntryName(expectedDrawableId)
        } catch (e: Resources.NotFoundException) {
            logError(e, "[ViewMatchers]")
            return false
        }

        val theme = target.context.theme
        val expectedDrawable = ResourcesCompat.getDrawable(resources, expectedDrawableId, theme) ?: return false

        val bitmap = getBitmap(target.drawable)
        val otherBitmap = getBitmap(expectedDrawable)
        return bitmap.sameAs(otherBitmap)
    }

    private fun getBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    override fun describeTo(description: Description) {
        description.appendText("with drawable from resource id: ")
        description.appendValue(expectedDrawableId)
        if (resourceName != null) {
            description.appendText("[")
            description.appendText(resourceName)
            description.appendText("]")
        }
    }
}

fun withTextAndParameters(resourceId: Int, vararg extraContent: String, isHint: Boolean = false) = object: TypeSafeMatcher<View>() {
    private var resourceName: String? = null
    private var expectedText: String? = null

    public override fun matchesSafely(target: View): Boolean {
        if (target !is TextView) return false

        val resources = target.context.resources
        try {
            resourceName = resources.getResourceEntryName(resourceId)
            expectedText = resources.getString(resourceId, extraContent)
        } catch (e: Resources.NotFoundException) {
            logError(e, "[ViewMatcherExtensions][withTextAndParameters]")
            return false
        }

        val currentText = when {
            isHint -> target.hint.toString()
            else -> target.text.toString()
        }

        return when {
            expectedText.isNullOrBlank() || currentText.isBlank() -> false
            else -> expectedText.equals(currentText)
        }
    }

    override fun describeTo(description: Description) {
        if (null != resourceName) {
            description.appendText("resourceName: [")
            description.appendText(resourceName)
            description.appendText("]")
        }
        if (null != expectedText) {
            description.appendText(" value: [")
            description.appendText(expectedText)
            description.appendText("]")
        }
    }
}