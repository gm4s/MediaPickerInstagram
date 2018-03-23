package io.freshdroid.mediapickerinstagram

import android.support.multidex.MultiDexApplication
import timber.log.Timber


class MediaPickerInstagramApplication : MultiDexApplication() {

    private val component: MediaPickerInstagramApplicationComponent by lazy {
        DaggerMediaPickerInstagramApplicationComponent.builder()
                .mediaPickerInstagramApplicationModule(MediaPickerInstagramApplicationModule(applicationContext, this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        component().inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

    fun component(): MediaPickerInstagramApplicationComponent {
        return component
    }

}