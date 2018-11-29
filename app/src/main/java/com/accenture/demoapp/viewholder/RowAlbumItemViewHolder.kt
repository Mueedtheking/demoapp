package com.accenture.demoapp.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.accenture.demoapp.models.AlbumsModel
import com.accenture.demoappapp.R


class RowAlbumItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val tvAlbumIdValue: TextView
    private val tvAlbumUserIdValue: TextView
    private val tvAlbumTitleValue: TextView

    init {
        tvAlbumIdValue = v.findViewById(R.id.tvAlbumIdValue)
        tvAlbumUserIdValue = v.findViewById(R.id.tvAlbumUserIdValue)
        tvAlbumTitleValue = v.findViewById(R.id.tvAlbumTitleValue)
    }

    fun invalidate(pos: Int, data: AlbumsModel) {

        tvAlbumIdValue.text = data?.id?.toString()
        tvAlbumUserIdValue.text = data?.userId?.toString()
        tvAlbumTitleValue.text = data?.title

    }


}