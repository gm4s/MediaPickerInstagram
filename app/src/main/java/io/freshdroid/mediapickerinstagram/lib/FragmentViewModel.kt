package io.freshdroid.mediapickerinstagram.lib

import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber


open class FragmentViewModel {

    private val arguments = PublishSubject.create<Bundle>()
    private val activityResult = PublishSubject.create<ActivityResult>()

    fun arguments(bundle: Bundle?) {
        bundle?.let {
            this.arguments.onNext(bundle)
        }
    }

    fun activityResult(activityResult: ActivityResult) {
        this.activityResult.onNext(activityResult)
    }

    @CallSuper
    open fun onCreateView(context: Context, savedInstanceState: Bundle?) {
        Timber.v("onCreateView %s", this.toString())
    }

    @CallSuper
    open fun <ViewType> onResume(view: ViewType) {
        Timber.v("onResume %s", this.toString())
    }

    @CallSuper
    open fun onPause() {
        Timber.v("onPause %s", this.toString())
    }

    @CallSuper
    open fun onDestroy() {
        Timber.v("onDestroy %s", this.toString())
    }

    protected fun arguments(): Observable<Bundle> {
        return arguments
    }

    protected fun activityResult(): Observable<ActivityResult> {
        return activityResult
    }

}