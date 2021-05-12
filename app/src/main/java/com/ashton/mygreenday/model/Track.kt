package com.ashton.mygreenday.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Track(
    @PrimaryKey
    @SerializedName("trackId")
    @ColumnInfo(name = "track_id")
    val id: Int?,

    @SerializedName("trackName")
    @ColumnInfo(name = "track_name")
    val trackName: String,

    @SerializedName("collectionName")
    @ColumnInfo(name = "collection_name")
    val collectionName: String,

    @SerializedName("artistName")
    @ColumnInfo(name = "artist_name")
    val artistName: String,

    @SerializedName("artworkUrl60")
    @ColumnInfo(name = "artwork_url_60")
    val artworkUrl60: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean
){
    override fun toString(): String {
        return favorite.toString()
    }
}