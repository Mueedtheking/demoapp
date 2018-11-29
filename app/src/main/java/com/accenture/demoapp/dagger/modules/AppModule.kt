package com.accenture.demoapp.dagger.modules

import com.accenture.demoapp.DemoApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var mApplication: DemoApplication) {

    @Singleton
    @Provides
    fun provideApplication(): DemoApplication {
        return mApplication
    }
}