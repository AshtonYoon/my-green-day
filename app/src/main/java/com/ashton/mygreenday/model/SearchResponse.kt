package com.ashton.mygreenday.model

import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("resultCount")
    val week: Int,

    @SerializedName("results")
    val list: List<Track>
)