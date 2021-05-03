package com.ashton.mygreenday.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ashton.mygreenday.model.Track
import com.ashton.mygreenday.repository.Repository

class TrackViewModel(application: Application): AndroidViewModel(application) {
    private val repository =
        Repository(application)

    val tracks = repository.getGreendaySongs()
    val favoriteTracks = repository.getFavoriteSongs()

    fun update(track: Track){
        repository.update(track)
    }
}