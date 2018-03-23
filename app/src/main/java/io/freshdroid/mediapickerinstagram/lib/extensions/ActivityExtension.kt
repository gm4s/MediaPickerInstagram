package io.freshdroid.mediapickerinstagram.lib.extensions

import android.app.Activity
import android.support.annotation.IdRes
import android.view.View


infix fun <T : View> Activity.bind(@IdRes res: Int): Lazy<T> {
    return unsafeLazy { findViewById<T>(res) }
}

private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)