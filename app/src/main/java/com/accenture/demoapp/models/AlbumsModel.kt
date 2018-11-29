package com.accenture.demoapp.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName

@Entity(tableName = "albums")
class AlbumsModel {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @SerializedName("aid")
    var aid: Int = 0

    @SerializedName("id")
    var id: Long = 0

    @SerializedName("userId")
    var userId: Long = 0

    @SerializedName("title")
    var title: String? = null
}