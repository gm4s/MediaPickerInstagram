package io.freshdroid.mediapickerinstagram.lib

import android.arch.lifecycle.Lifecycle
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.kotlin.autoDisposable
import io.freshdroid.mediapickerinstagram.MediaPickerInstagramApplication
import io.freshdroid.mediapickerinstagram.MediaPickerInstagramApplicationComponent
import io.freshdroid.mediapickerinstagram.lib.utils.BundleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

typealias ActivityViewModelConstructor = (Environment, AndroidLifecycleScopeProvider) -> ActivityViewModel

private const val VIEW_MODEL_KEY = "viewModel"

open class BaseActivity<ViewModelType : ActivityViewModel> : AppCompatActivity() {

    protected val scopeProvider: AndroidLifecycleScopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

    protected lateinit var viewModel: ViewModelType

    private val back = PublishSubject.create<Boolean>()

    protected fun attachViewModel(viewModelSupplier: ActivityViewModelConstructor, savedInstanceState: Bundle?) {
        viewModel = ActivityViewModelManager.fetch(this, scopeProvider, viewModelSupplier, BundleUtils.maybeGetBundle(savedInstanceState, VIEW_MODEL_KEY))
    }

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        viewModel.activityResult(ActivityResult.create(requestCode, resultCode, intent))
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v("onCreate %s", this.toString())
        viewModel.intent(intent)
    }

    @CallSuper
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        viewModel.intent(intent)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        Timber.v("onStart %s", this.toString())
        back
                .observeOn(AndroidSchedulers.mainThread())
                .autoDisposable(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_STOP))
                .subscribe { _ -> goBack() }
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        Timber.v("onResume %s", this.toString())
        viewModel.onResume(this)
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        Timber.v("onPause %s", this.toString())
        viewModel.onPause()
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        Timber.v("onStop %s", this.toString())
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        Timber.v("onDestroy %s", this.toString())

        if (isFinishing) {
            ActivityViewModelManager.destroy(viewModel)
        }
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        Timber.v("onSaveInstanceState %s", this.toString())

        var viewModelEnvelope = Bundle()
        viewModelEnvelope = ActivityViewModelManager.save(viewModel, viewModelEnvelope)

        outState.putBundle(VIEW_MODEL_KEY, viewModelEnvelope)

        super.onSaveInstanceState(outState)
    }

    @CallSuper
    override fun onBackPressed() {
        back()
    }

    protected open fun exitTransition(): Pair<Int, Int>? {
        return null
    }

    protected fun back() {
        back.onNext(true)
    }

    protected fun application(): MediaPickerInstagramApplication = application as MediaPickerInstagramApplication

    protected fun component(): MediaPickerInstagramApplicationComponent = application().component()

    protected fun environment(): Environment = component().environment()

    private fun goBack() {
        super.onBackPressed()
        val exitTransitions = exitTransition()
        if (exitTransitions != null) {
            overridePendingTransition(exitTransitions.first, exitTransitions.second)
        }
    }

}