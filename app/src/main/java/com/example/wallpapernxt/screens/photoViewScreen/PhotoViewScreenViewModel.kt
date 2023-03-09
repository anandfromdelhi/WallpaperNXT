package com.example.wallpapernxt.screens.photoViewScreen

import androidx.lifecycle.ViewModel
import com.example.wallpapernxt.data.DataOrException
import com.example.wallpapernxt.model.photoId.photoId
import com.example.wallpapernxt.model.searchPhotos.searchPhotos
import com.example.wallpapernxt.repository.PhotoViewRepository
import com.example.wallpapernxt.repository.SearchPhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoViewScreenViewModel @Inject constructor(
    private val repository: PhotoViewRepository
): ViewModel() {
    suspend fun viewPhoto(path:String)
            : DataOrException<photoId, Boolean, Exception> {
        return repository.viewPhoto(path = path)
    }
}