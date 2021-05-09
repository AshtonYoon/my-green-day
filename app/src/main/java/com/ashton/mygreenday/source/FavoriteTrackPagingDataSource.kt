package com.ashton.mygreenday.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ashton.mygreenday.dao.TrackDao
import com.ashton.mygreenday.model.Track

class FavoriteTrackPagingDataSource(private val trackDao: TrackDao) : PagingSource<Int, Track>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
        val favoriteTracks = trackDao.getFavoriteTracks()

        return LoadResult.Page(
            data = favoriteTracks,
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Track>): Int? = null
}