package com.example.wallpapernxt.screens.photoGalleryScreen

import androidx.lifecycle.ViewModel
import com.example.wallpapernxt.data.DataOrException
import com.example.wallpapernxt.model.searchPhotos.searchPhotos
import com.example.wallpapernxt.repository.SearchPhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoGalleryScreenViewModel @Inject constructor(
    private val repository: SearchPhotosRepository
):ViewModel() {
    suspend fun searchPhotos(query: String, page: Int)
    :DataOrException<searchPhotos,Boolean,Exception>{
        return repository.searchPhotos(query = query, page = page)
    }
}