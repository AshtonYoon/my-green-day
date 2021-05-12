package com.ashton.mygreenday.dao

import androidx.room.*
import com.ashton.mygreenday.model.Track

@Dao
interface TrackDao {
    @Query("SELECT * FROM Track LIMIT :offset, :size")
    suspend fun getTracks(offset: Int, size: Int): List<Track>

    @Query("SELECT * FROM Track WHERE favorite = 1")
    suspend fun getFavoriteTracks(): List<Track>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tracks: List<Track>)

    @Update
    suspend fun update(track: Track)
}