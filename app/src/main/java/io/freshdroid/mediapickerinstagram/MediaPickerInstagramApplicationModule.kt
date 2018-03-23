package io.freshdroid.mediapickerinstagram

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageInfo
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import io.freshdroid.mediapickerinstagram.lib.Build
import io.freshdroid.mediapickerinstagram.lib.Environment
import io.freshdroid.mediapickerinstagram.lib.qualifiers.ApplicationContext
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Singleton

@Module
class MediaPickerInstagramApplicationModule(
        private val context: Context,
        private val application: Application
) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun providePackageInfo(application: Application): PackageInfo =
            application.packageManager.getPackageInfo(application.packageName, 0)

    @Provides
    @Singleton
    fun provideBuild(packageInfo: PackageInfo): Build = Build(packageInfo)

    @Provides
    @Singleton
    fun provideLocale(): Locale = Locale.getDefault()

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    @Provides
    @Singleton
    fun provideEnvironment(scheduler: Scheduler): Environment {
        return Environment(scheduler)
    }

    @Provides
    @Singleton
    fun provideScheduler(): Scheduler = Schedulers.computation()

}