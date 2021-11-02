package com.example.unsplash.network

import com.example.unsplash.db.models.Photos
import com.example.unsplash.utils.Constants.API_KEY
import com.example.unsplash.utils.Constants.PAGE_SIZE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotosApi {
    @Headers("Authorization: $API_KEY")
    @GET("v1/curated")
    suspend fun getCuratedPhotos(
    @Query("per_page") per_page : Int = PAGE_SIZE
    ) : Photos

    @Headers("Authorization: $API_KEY")  // check extra spaces
    @GET("v1/search")
    suspend fun getSearchPhotos(
        @Query("query") query:String,
        @Query("per_page") per_page : Int = PAGE_SIZE
    ) : Photos
}