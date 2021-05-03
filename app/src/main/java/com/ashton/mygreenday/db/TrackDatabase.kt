package com.ashton.mygreenday.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ashton.mygreenday.dao.TrackDao
import com.ashton.mygreenday.model.Track

@Database(entities = [Track::class], version = 1)
abstract class TrackDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao

    companion object{
        private var INSTANCE : TrackDatabase? = null

        fun getInstance(context: Context): TrackDatabase? {
            if (INSTANCE == null) {
                synchronized(TrackDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        TrackDatabase::class.java, "track")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}