package com.accenture.demoapp.network

import com.accenture.demoapp.models.AlbumsModel
import retrofit2.Call
import retrofit2.http.GET

interface Restapi {

    @GET("albums")
    fun getAllAlbums(): Call<List<AlbumsModel>>

}