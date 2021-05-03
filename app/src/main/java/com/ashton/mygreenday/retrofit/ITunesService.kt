package com.ashton.mygreenday.retrofit

import com.ashton.mygreenday.model.SearchResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesService {
    @GET("search")
    suspend fun search(
            @Query("term") term: String = "greenday",
            @Query("entity") entity: String = "song",
            @Query("offset") offset: Int,
            @Query("limit") limit: Int
    ): Response<SearchResponse>
}