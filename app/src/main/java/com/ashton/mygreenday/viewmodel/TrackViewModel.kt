package com.ashton.mygreenday.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ashton.mygreenday.model.Track
import com.ashton.mygreenday.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.launch

class TrackViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)

    val tracks = repository.getGreendaySongs()
    val favoriteTracks = repository.getFavoriteSongs()

    suspend fun update(track: Track) = viewModelScope.launch {
        repository.update(track)
    }
}