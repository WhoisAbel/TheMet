package io.github.metmuseum.themet

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.github.metmuseum.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class TheMetApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}