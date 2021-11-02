package com.example.unsplash.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.unsplash.db.models.Photo
import com.example.unsplash.db.models.Photos
import com.example.unsplash.utils.Converters

@Database(entities = [Photo::class],version = 1)
@TypeConverters(Converters::class)
abstract class PhotosDatabase : RoomDatabase() {
    abstract fun photosDao() : PhotosDao
}