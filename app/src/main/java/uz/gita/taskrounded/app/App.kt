package uz.gita.taskrounded.app

import android.app.Application
import timber.log.Timber
import uz.gita.taskrounded.BuildConfig

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}