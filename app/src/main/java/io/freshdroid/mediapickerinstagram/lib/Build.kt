package io.freshdroid.mediapickerinstagram.lib

import android.content.pm.PackageInfo
import io.freshdroid.mediapickerinstagram.BuildConfig


open class Build(
        private val packageInfo: PackageInfo
) {

    fun applicationId(): String {
        return packageInfo.packageName
    }

    fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    fun isRelease(): Boolean {
        return !BuildConfig.DEBUG
    }

    fun versionCode(): Int {
        return packageInfo.versionCode
    }

    fun versionName(): String {
        return packageInfo.versionName
    }

}