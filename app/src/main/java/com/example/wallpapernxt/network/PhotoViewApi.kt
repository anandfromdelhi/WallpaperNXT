package com.example.wallpapernxt.network

import com.example.wallpapernxt.model.photoId.photoId
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface PhotoViewApi {
    @GET(value = "photos/{path}")
    suspend fun viewPhoto(
        @Path("path")path:String,
        @Query("client_id")client_id:String
    ): photoId
}