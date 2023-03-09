package com.example.wallpapernxt.network

import com.example.wallpapernxt.model.searchPhotos.searchPhotos
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface SearchPhotosApi {
    @GET(value = "search/photos")
    suspend fun searchPhotos(
        @Query("page")page:Int,
        @Query("query")query:String,
        @Query("client_id")client_id:String,
        @Query("per_page")per_page:Int,
        @Query("orientation")orientation:String
    ):searchPhotos
}