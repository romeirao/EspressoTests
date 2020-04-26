package wanderley.romeiro.italo.espressotests.util

import android.util.Log
import wanderley.romeiro.italo.espressotests.BuildConfig
import java.lang.reflect.UndeclaredThrowableException

inline val <reified T : Any> T.GENERIC_TAG: String
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