package com.accenture.demo.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.accenture.demoapp.models.AlbumsModel
import com.accenture.demoapp.viewholder.RowAlbumItemViewHolder
import com.accenture.demoappapp.R

class AlbumListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var albumsList = ArrayList<AlbumsModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.album_list_item, parent, false)
            return RowAlbumItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RowAlbumItemViewHolder).invalidate(position, albumsList[position])

    }

    fun setData(newData: List<AlbumsModel>) {
        if (albumsList != null) {

            val postDiffCallback = PostDiffCallback(albumsList, newData)
            val diffResult = DiffUtil.calculateDiff(postDiffCallback)
            albumsList.clear()
            albumsList.addAll(newData)
            diffResult.dispatchUpdatesTo(this)
        } else {

            albumsList = ArrayList(newData)
            notifyDataSetChanged()
        }
    }

    fun clearData() {
        if (albumsList != null) {
            if (albumsList.size > 0) albumsList.clear()
        }
    }



    override fun getItemCount(): Int {
        return albumsList.size
    }

    internal inner class PostDiffCallback(private val oldPosts: List<AlbumsModel>, private val newPosts: List<AlbumsModel>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldPosts.size
        }

        override fun getNewListSize(): Int {
            return newPosts.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldPosts[oldItemPosition].id === newPosts[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldPosts[oldItemPosition].equals(newPosts[newItemPosition])
        }
    }
}