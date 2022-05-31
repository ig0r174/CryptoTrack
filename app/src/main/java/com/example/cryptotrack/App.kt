package com.example.cryptotrack

import android.app.Application
import android.content.Context
import com.example.cryptotrack.di.AppModule
import com.example.cryptotrack.di.DaggerIAppComponent
import com.example.cryptotrack.di.IAppComponent

class App: Application() {
    lateinit var appComponent: IAppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerIAppComponent.builder().appModule(AppModule(this)).build()
    }
}

fun Context.findAppComponent(): IAppComponent =
    (this.applicationContext as App).appComponent
