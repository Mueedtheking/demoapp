package com.accenture.demoapp.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.accenture.demoapp.models.AlbumsModel

@Dao
interface DaoOperations {

    @Query("SELECT * FROM albums order by title")
    fun getAllAlbums(): LiveData<List<AlbumsModel>>

    @Query("DELETE FROM albums")
    fun deleteAllAlbums()

    @Insert
    fun insertAllAlbums(albumsList: List<AlbumsModel>)

}