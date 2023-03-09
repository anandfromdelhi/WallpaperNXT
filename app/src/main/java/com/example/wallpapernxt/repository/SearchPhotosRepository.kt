package com.example.wallpapernxt.repository

import com.example.wallpapernxt.data.DataOrException
import com.example.wallpapernxt.model.searchPhotos.searchPhotos
import com.example.wallpapernxt.network.SearchPhotosApi
import com.example.wallpapernxt.utils.constants.Constants
import javax.inject.Inject

class SearchPhotosRepository @Inject constructor(
    private val api: SearchPhotosApi
) {
    suspend fun searchPhotos(
        query: String,
        page: Int
    ) : DataOrException<searchPhotos, Boolean, Exception> {
        val response = try {
            api.searchPhotos(
                query = query,
                client_id = Constants.ACCESS_KEY,
                page = page,
                per_page = 30,
                orientation = "portrait"
            )
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}