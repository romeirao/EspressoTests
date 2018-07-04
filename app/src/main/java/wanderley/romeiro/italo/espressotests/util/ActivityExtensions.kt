package wanderley.romeiro.italo.espressotests.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager

fun <T> Activity.navigateTo(context: Context, clazz: Class<T>) {
  val intent = Intent(context, clazz)
  startActivity(intent)
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