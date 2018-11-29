package com.accenture.demoapp.dagger.components

import com.accenture.demoapp.dagger.modules.AppModule
import com.accenture.demoapp.dagger.modules.NetworkModule
import com.accenture.demoapp.view.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface NetworkComponent {
    fun injectMain(activity: MainActivity)
}