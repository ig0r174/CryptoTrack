package com.example.cryptotrack.di

import com.example.cryptotrack.viewmodels.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppBindsModule::class,
])
interface IAppComponent {
    fun viewModelFactory(): ViewModelFactory
}