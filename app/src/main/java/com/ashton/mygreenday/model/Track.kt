package com.ashton.mygreenday.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Track(
    @PrimaryKey(autoGenerate = true)
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

    @SerializedName("artworkUrl100")
    @ColumnInfo(name = "artworkUrl100")
    val artworkUrl100: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean
){
    override fun toString(): String {
        return favorite.toString()
    }
}