package com.example.unsplash.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unsplash.db.models.Photo
import com.example.unsplash.db.models.Photos
import kotlinx.coroutines.flow.Flow


@Dao
interface PhotosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photos:List<Photo>)

    @Query("Select * from photos")
    fun getAllPhotos() : Flow<List<Photo>>

    @Query("Delete from photos")
    suspend fun deleteAllPhotos()
}