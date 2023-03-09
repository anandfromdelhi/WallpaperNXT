package com.example.wallpapernxt.network

import com.example.wallpapernxt.model.searchCategories.searchCategories
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchCategoriesApi {
    @GET(value = "search/collections")
    suspend fun searchCategories(
        @Query("page")page:Int,
        @Query("query")query:String,
        @Query("client_id")client_id:String
    ): searchCategories
}