package jp.kaleidot725.githubclient.android

import android.app.Application
import jp.kaleidot725.githubclient.android.di.setupKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin(applicationContext)
    }
}