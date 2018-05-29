package wanderley.romeiro.italo.espressotests.util

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import wanderley.romeiro.italo.espressotests.BuildConfig
import java.lang.reflect.UndeclaredThrowableException

inline val <reified T: Any> T.GENERIC_TAG: String
    get() = javaClass.simpleName

fun Any.log(msg: String, tag: String? = null) {
    if (BuildConfig.DEBUG) {
        Log.d("${tag.orEmpty()}[$GENERIC_TAG]", msg)
    }
}

fun Any.logError(throwable: Throwable, msg: String? = null, tag: String? = null) {
    val msgError = when (throwable) {
        is UndeclaredThrowableException -> throwable.cause?.message
        else -> throwable.message
    }
    val realThrowable = when (throwable) {
        is UndeclaredThrowableException -> throwable.cause
        else -> throwable
    }

    if (BuildConfig.DEBUG) {
        Log.e("${tag.orEmpty()}[$GENERIC_TAG]", msg ?: msgError ?: "[EXCEPTION]", realThrowable)
    }
}

fun Activity.showKeyboard(view: View) {
    if (view.requestFocus()) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Activity.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}