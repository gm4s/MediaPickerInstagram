package io.freshdroid.mediapickerinstagram.lib.extensions

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.v4.content.ContextCompat
import android.view.View


infix fun <T : View> View.bind(@IdRes res: Int): Lazy<T> {
    return unsafeLazy { findViewById<T>(res) }
}

infix fun View.getCompatDrawable(@DrawableRes res: Int): Lazy<Drawable?> {
    return unsafeLazy { ContextCompat.getDrawable(context, res) }
}

private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
