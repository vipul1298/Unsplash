package com.example.unsplash.di

import android.app.Application
import androidx.room.Room
import com.example.unsplash.db.PhotosDatabase
import com.example.unsplash.network.PhotosApi
import com.example.unsplash.repository.PhotoRepository
import com.example.unsplash.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePhotoRepository(api: PhotosApi,db:PhotosDatabase) = PhotoRepository(api,db)

    @Singleton
    @Provides
    fun providePhotosApi() : PhotosApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PhotosApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(app:Application) : PhotosDatabase =
        Room.databaseBuilder(app,PhotosDatabase::class.java,"photos_database")
            .build()

}