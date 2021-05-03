package com.ashton.mygreenday.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ashton.mygreenday.model.Track

@Dao
interface TrackDao {
    @Query("SELECT * FROM Track")
    fun getAllTracks(): LiveData<List<Track>>

    @Query("SELECT * FROM Track WHERE favorite = 1")
    fun getFavoriteTracks(): LiveData<List<Track>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(track: Track)

    @Update
    fun update(track: Track)
}