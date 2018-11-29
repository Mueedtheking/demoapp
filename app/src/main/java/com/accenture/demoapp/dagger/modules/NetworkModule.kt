package com.accenture.demoapp.dagger.modules

import android.arch.persistence.room.Room
import com.accenture.demoapp.DemoApplication
import com.accenture.demoapp.DemoDatabase
import com.accenture.demoapp.network.Restapi
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule(var mBaseUrl: String) {
    @Singleton
    @Provides
    fun provideRetrofit(): Restapi {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setLenient()
                .create()
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build().create(Restapi::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(): DemoDatabase {
        return Room.databaseBuilder(DemoApplication.applicationContext(),
                DemoDatabase::class.java!!, "demo_db").fallbackToDestructiveMigration()
                .build()
    }
}