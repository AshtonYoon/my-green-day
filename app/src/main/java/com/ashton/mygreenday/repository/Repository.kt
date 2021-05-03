package com.ashton.mygreenday.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ashton.mygreenday.dao.TrackDao
import com.ashton.mygreenday.db.TrackDatabase
import com.ashton.mygreenday.model.SearchResponse
import com.ashton.mygreenday.model.Track
import com.ashton.mygreenday.retrofit.ITunesService
import com.ashton.mygreenday.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(application: Application) {
    private val client: ITunesService = RetrofitClient.service

    private val trackDatabase: TrackDatabase = TrackDatabase.getInstance(application)!!
    private val trackDao: TrackDao = trackDatabase.trackDao()
    private val tracks: LiveData<List<Track>> = trackDao.getAllTracks()
    private val favoriteTracks: LiveData<List<Track>> = trackDao.getFavoriteTracks()

    fun getGreendaySongs(): LiveData<List<Track>> {
        client.search().enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    response.body()?.list.orEmpty().forEach {
                        insert(it)
                    }
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                t.stackTrace
            }
        })

        return tracks
    }

    fun getFavoriteSongs(): LiveData<List<Track>> {
        return favoriteTracks
    }

    fun insert(track: Track) {
        trackDao.insert(track)
    }

    fun update(track: Track) {
        trackDao.update(track)
    }
}