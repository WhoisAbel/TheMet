package io.github.metmuseum.themet.common.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Ignore Exception thrown in AutoClearedValue for access to after it becomes null
 * */
fun CoroutineScope.launchWithErrorHandler(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> Unit
) = run {
    val errorHandler = CoroutineExceptionHandler { context, error ->
        when (error) {
            is IllegalStateException -> {
                if (error.message != AutoClearedValue.EXCEPTION_ON_NULL_ACCESS) {
                    throw error
                } else {
                    Timber.tag("AutoClearedValueTAG")
                        .d("Tried to access value after fragment view is destroyed")
                }
            }
            else -> throw error
        }
    }


    launch(
        context = context + errorHandler,
        block = block
    )
}


fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun showKeyboard(view: TextInputEditText) {
    val inputMethodManager =
        view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(
        InputMethodManager.SHOW_FORCED,
        InputMethodManager.HIDE_IMPLICIT_ONLY
    )
}
