package com.accenture.demoapp

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.accenture.demoapp.database.DaoOperations
import com.accenture.demoapp.models.AlbumsModel

@Database(entities = arrayOf(AlbumsModel::class), version = 1, exportSchema = false)
abstract class DemoDatabase : RoomDatabase() {
    abstract fun daoOperations(): DaoOperations
}