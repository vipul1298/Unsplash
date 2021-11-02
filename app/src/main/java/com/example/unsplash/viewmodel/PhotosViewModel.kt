package com.example.unsplash.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.unsplash.db.models.Photo
import com.example.unsplash.db.models.Photos
import com.example.unsplash.repository.PhotoRepository
import com.example.unsplash.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: PhotoRepository
) : ViewModel() {


    private var pics : MutableLiveData<Resource<List<Photo>>> = MutableLiveData()
    val photos : LiveData<Resource<List<Photo>>> = pics


    init {
        getCuratedPhotos()
    }


   private fun getCuratedPhotos(){
       viewModelScope.launch {
           repository.getCuratedPhotos().collect{
               pics.value=it
           }
           Log.d("VIEWMODEL", "------------getCuratedPhotos---------------: ${photos.value}")

       }

   }



   fun getSearchPhotos(query:String){
           viewModelScope.launch {
                repository.getSearchPhotos(query).collect {
                   pics.value=it
               }
               Log.d("VIEWMODEL", "------------getSearchPhotos-------------: ${photos.value}")

           }
   }
//
//
//
//

//
//
//
//
//    private fun getCuratedPhotos(){
//       viewModelScope.launch() {
//           loading.value = true
//           val result = repository.getCuratedPhotos(page = 1)
//           Log.d("------VIEWMODEL------", "getCuratedPhotos: ${result.data}")
//           photos.value=result
//
//       }
//   }
//
//    fun getSearchPhotos(query:String){
//        viewModelScope.launch() {
//            loading.value =true
//
//            val result = repository.getSearchPhotos(query = query)
//            Log.d("------VIEWMODEL------", "getSearchPhotos: ${result.data}")
//
//            photos.value=result
//
//        }
//    }

}