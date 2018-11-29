package com.accenture.demoapp.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.accenture.demo.adapter.AlbumListAdapter
import com.accenture.demo.viewmodel.AlbumsViewModel
import com.accenture.demoapp.models.AlbumsModel
import com.accenture.demoapp.view.activities.MainActivity
import com.accenture.demoappapp.R
import kotlinx.android.synthetic.main.fragment_albums.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback


class AlbumListFragment : Fragment() {

    private var linearLayoutManager: LinearLayoutManager? = null
    private var albumListAdapter: AlbumListAdapter? = null
    private var albumsArrayList = ArrayList<AlbumsModel>()
    var albumsViewModel: AlbumsViewModel? = null
    var getAlbumResponse: Call<List<AlbumsModel>>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumsViewModel = ViewModelProviders.of(this).get(AlbumsViewModel::class.java)
        albumListAdapter = AlbumListAdapter()
        linearLayoutManager = LinearLayoutManager(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_albums, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val albumListObserver = Observer<List<AlbumsModel>> { albumsList ->
            albumListAdapter?.setData(albumsList!!)
        }
        albumsViewModel?.setDao((activity as MainActivity).getDaoOperations()!!)
        albumsViewModel?.getAllAlbums()?.observe(this, albumListObserver)
        rvAlbums.layoutManager = linearLayoutManager
        rvAlbums.adapter = albumListAdapter


        swipeRefreshLayoutAList.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            swipeRefreshLayoutAList.isRefreshing = false
            getAlbums()
        })
        getAlbums()
    }

    fun getAlbums() {
        swipeRefreshLayoutAList.isRefreshing = true
        getAlbumResponse = (activity as MainActivity).getRestClient()?.getAllAlbums()!!

        getAlbumResponse!!.enqueue(object : Callback<List<AlbumsModel>> {
            override fun onResponse(call: Call<List<AlbumsModel>>, response: retrofit2.Response<List<AlbumsModel>>?) {
                if (!getAlbumResponse!!.isCanceled) {
                    swipeRefreshLayoutAList.isRefreshing = false

                    if (response?.body() != null) {
                        if (!albumsArrayList.isEmpty()!!) {
                            albumsArrayList.clear()
                            }

                            albumsArrayList = ArrayList(response?.body())

                            doAsync {
                                    albumsViewModel?.deleteAllAlbums()
                                    albumsViewModel?.saveAllAlbums(albumsArrayList)
                            }


                    }
                        (activity as MainActivity).hideSplash()

                }

            }

            override fun onFailure(call: Call<List<AlbumsModel>>, t: Throwable) {
                if (!getAlbumResponse!!.isCanceled) {
                    (activity as MainActivity).hideSplash()
                    swipeRefreshLayoutAList.isRefreshing = false
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getAlbumResponse?.cancel()
    }


}