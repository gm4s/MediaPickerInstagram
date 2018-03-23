package io.freshdroid.mediapickerinstagram.lib.utils

import android.os.Looper


object ThreadPredicate {

    fun isMainThread(): Boolean {
        return Looper.getMainLooper().thread == Thread.currentThread()
    }

}