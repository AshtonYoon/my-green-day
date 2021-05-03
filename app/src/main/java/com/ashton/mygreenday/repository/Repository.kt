package com.ashton.mygreenday.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.ashton.mygreenday.dao.TrackDao
import com.ashton.mygreenday.db.TrackDatabase
import com.ashton.mygreenday.model.SearchResponse
import com.ashton.mygreenday.model.Track
import com.ashton.mygreenday.retrofit.ITunesService
import com.ashton.mygreenday.retrofit.RetrofitClient
import com.ashton.mygreenday.source.FavoriteTrackPagingDataSource
import com.ashton.mygreenday.source.TrackPagingDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(application: Application) {
    private val client: ITunesService = RetrofitClient.service

    private val trackDatabase: TrackDatabase = TrackDatabase.getInstance(application)!!
    private val trackDao: TrackDao = trackDatabase.trackDao()

    fun getGreendaySongs()=
        Pager(
            config = PagingConfig(pageSize = TrackPagingDataSource.PAGE_SIZE),
            pagingSourceFactory = { TrackPagingDataSource(RetrofitClient.service, trackDao) }
        ).liveData

    fun getFavoriteSongs() =
        Pager(
            config = PagingConfig(pageSize = TrackPagingDataSource.PAGE_SIZE),
            pagingSourceFactory = { FavoriteTrackPagingDataSource(trackDao) }
        ).liveData

    suspend fun update(track: Track) {
        trackDao.update(track)
    }
}
