package com.accenture.demo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.accenture.demoapp.database.DaoOperations
import com.accenture.demoapp.models.AlbumsModel
import java.util.*

class AlbumsViewModel(application: Application) : AndroidViewModel(application) {

    private var daoOperations: DaoOperations? = null

    internal fun getAllAlbums(): LiveData<List<AlbumsModel>>
    {
        return daoOperations?.getAllAlbums()!!
    }

    fun setDao(daoOperations: DaoOperations) {
        this.daoOperations = daoOperations
    }

    internal fun saveAllAlbums(albumsList: List<AlbumsModel>) {
     daoOperations?.insertAllAlbums(Collections.synchronizedList(albumsList))
    }

    internal fun deleteAllAlbums() {
        daoOperations?.deleteAllAlbums()
    }


}
