package com.example.wallpapernxt.repository

import com.example.wallpapernxt.data.DataOrException
import com.example.wallpapernxt.model.photoId.photoId
import com.example.wallpapernxt.network.PhotoViewApi
import com.example.wallpapernxt.utils.constants.Constants
import javax.inject.Inject



class PhotoViewRepository @Inject constructor(
    private val api: PhotoViewApi
) {
    suspend fun viewPhoto(
             path:String
    ) : DataOrException<photoId, Boolean, Exception> {
        val response = try {
            api.viewPhoto(
                client_id = Constants.ACCESS_KEY,
                path = path
            )
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}