package com.ashton.mygreenday.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ashton.mygreenday.dao.TrackDao
import com.ashton.mygreenday.model.Track
import com.ashton.mygreenday.retrofit.ITunesService

class TrackPagingDataSource(private val service: ITunesService, private val trackDao: TrackDao) :
    PagingSource<Int, Track>() {
    private var offset = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
        offset = params.key ?: 0

        return try {
            var tracks = trackDao.getTracks(offset, PAGE_SIZE)

            val tracksSize = tracks.size
            var nextPage: Int? = offset + PAGE_SIZE

            if (tracksSize < PAGE_SIZE) {
                val response =
                    service.search(offset = offset + tracksSize, limit = PAGE_SIZE - tracksSize)
                val data = response.body()?.list.orEmpty()

                // TODO: 데이터를 다 가져온 상태가 아님에도 불구하고 데이터의 개수가 리밋보다 적은 케이스가 있음
                // https://github.com/AshtonYoon/my-green-day/issues/1
                if (data.size != PAGE_SIZE) nextPage = null

                trackDao.insertAll(data)

                tracks =
                    tracks.plus(trackDao.getTracks(offset + tracksSize, PAGE_SIZE - tracksSize))
            }

            LoadResult.Page(
                data = tracks,
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Track>): Int = offset

    companion object {
        // 한 번에 최대로 가져올 수 있는 개수
        const val PAGE_SIZE = 50
    }
}