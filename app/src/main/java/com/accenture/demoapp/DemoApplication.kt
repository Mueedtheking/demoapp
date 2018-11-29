package com.accenture.demoapp

import android.app.Application
import android.content.Context
import com.accenture.demoapp.dagger.components.DaggerNetworkComponent
import com.accenture.demoapp.dagger.components.NetworkComponent
import com.accenture.demoapp.dagger.modules.AppModule
import com.accenture.demoapp.dagger.modules.NetworkModule
import com.accenture.demoappapp.BuildConfig

class  DemoApplication: Application(){
    private var mNetComponent: NetworkComponent? = null
    init {
        instance = this
    }

    companion object {
        private var instance: DemoApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        val context: Context = DemoApplication.applicationContext()
        mNetComponent = DaggerNetworkComponent.builder().appModule(AppModule(this))
                .networkModule( NetworkModule(BuildConfig.END_POINT))
                .build()
    }

    fun getNetComponent(): NetworkComponent? {
        return mNetComponent
    }
}