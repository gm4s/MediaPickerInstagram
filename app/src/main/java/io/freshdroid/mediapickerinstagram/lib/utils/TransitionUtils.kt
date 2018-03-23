package io.freshdroid.mediapickerinstagram.lib.utils

import android.app.Activity
import android.content.Context
import io.freshdroid.mediapickerinstagram.R


object TransitionUtils {

    /**
     * Explicitly set a transition after starting an activity.
     *
     * @param context The activity that started the new intent.
     * @param transition A pair of animation ids, first is the enter animation, second is the exit animation.
     */
    @JvmStatic
    fun transition(context: Context, transition: Pair<Int, Int>) {
        if (context !is Activity) {
            return
        }
        context.overridePendingTransition(transition.first, transition.second)
    }

    @JvmStatic
    fun enter(): Pair<Int, Int> {
        return Pair(R.anim.slide_in_right, R.anim.zoom_out)
    }

    @JvmStatic
    fun exit(): Pair<Int, Int> {
        return Pair(R.anim.zoom_in, R.anim.slide_out_right)
    }

    @JvmStatic
    fun slideInUp(): Pair<Int, Int> {
        return Pair(R.anim.slide_in_up, R.anim.stay)
    }

    @JvmStatic
    fun slideOutDown(): Pair<Int, Int> {
        return Pair(R.anim.stay, R.anim.slide_out_down)
    }

    @JvmStatic
    fun fadeIn(): Pair<Int, Int> {
        return Pair(R.anim.fade_in_full, R.anim.stay)
    }

    @JvmStatic
    fun fadeOut(): Pair<Int, Int> {
        return Pair(R.anim.stay, R.anim.fade_out_full)
    }

}