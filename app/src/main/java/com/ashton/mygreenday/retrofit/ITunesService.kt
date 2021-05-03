package com.ashton.mygreenday.retrofit

import com.ashton.mygreenday.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesService {
    @GET("search")
    fun search(
            @Query("term") term: String = "greenday",
            @Query("entity") entity: String = "song"
    ): Call<SearchResponse>
}