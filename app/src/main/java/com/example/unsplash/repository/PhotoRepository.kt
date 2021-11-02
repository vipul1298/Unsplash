package com.example.unsplash.repository

import androidx.room.withTransaction
import com.example.unsplash.db.PhotosDatabase
import com.example.unsplash.db.models.Photos
import com.example.unsplash.network.PhotosApi
import com.example.unsplash.utils.Resource
import com.example.unsplash.utils.networkBoundResource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class PhotoRepository @Inject constructor(
    private val api: PhotosApi,
    private val db: PhotosDatabase
) {

    private val photosDao = db.photosDao()

    fun getCuratedPhotos() = networkBoundResource(
        query = {
            photosDao.getAllPhotos()
        },
        fetch = {
            api.getCuratedPhotos()

        },
        saveFetchResult = { photos ->
            db.withTransaction {
                photosDao.deleteAllPhotos()
                photosDao.insertPhotos(photos.photos)
            }

        }

    )

    fun getSearchPhotos(query:String) = networkBoundResource(
        query = {
            photosDao.getAllPhotos()
        },
        fetch = {
            api.getSearchPhotos(query=query)
        },
        saveFetchResult = {photos ->
            db.withTransaction {
                photosDao.deleteAllPhotos()
                photosDao.insertPhotos(photos.photos)
            }
        }

    )


//    suspend fun getCuratedPhotos(page:Int) : Resource<Photos> {
//
//        val response = try {
//            api.getCuratedPhotos(page = page)
//        } catch (e: Exception){
//            return Resource.Error("Something went wrong...")
//        }
//        return Resource.Success(response)
//    }
//
//    suspend fun getSearchPhotos(query:String) : Resource<Photos> {
//        val response = try {
//            api.getSearchPhotos(query = query)
//        } catch (e: Exception){
//            return Resource.Error("Something went wrong...")
//        }
//        return Resource.Success(response)
//    }

}