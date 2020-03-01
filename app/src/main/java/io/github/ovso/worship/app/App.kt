package io.github.ovso.worship.app

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        LibraryInit(this)
    }

}
