package io.freshdroid.mediapickerinstagram.lib

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber


open class ActivityViewModel {

    private val activityResult = PublishSubject.create<ActivityResult>()
    private val intent = PublishSubject.create<Intent>()

    fun activityResult(activityResult: ActivityResult) {
        this.activityResult.onNext(activityResult)
    }

    fun intent(intent: Intent?) {
        intent?.let {
            this.intent.onNext(intent)
        }
    }

    @CallSuper
    fun onCreate(context: Context, savedInstanceState: Bundle?) {
        Timber.v("onCreate: %s", this.toString())
    }

    @CallSuper
    fun <ViewType> onResume(view: ViewType) {
        Timber.v("onResume: %s", this.toString())
    }

    @CallSuper
    fun onPause() {
        Timber.v("onPause: %s", this.toString())
    }

    @CallSuper
    fun onDestroy() {
        Timber.v("onDestroy: %s", this.toString())
    }

    protected fun activityResult(): Observable<ActivityResult> = activityResult

    protected fun intent(): Observable<Intent> = intent

}