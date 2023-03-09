package com.example.wallpapernxt.repository

import com.example.wallpapernxt.data.DataOrException
import com.example.wallpapernxt.model.searchCategories.searchCategories
import com.example.wallpapernxt.network.SearchCategoriesApi
import com.example.wallpapernxt.utils.constants.Constants
import javax.inject.Inject

class SearchCategoriesRepository  @Inject constructor(
    private val api: SearchCategoriesApi
) {
    suspend fun searchCategories(query: String, page: Int)
            : DataOrException<searchCategories, Boolean, Exception> {
        val response = try {
            api.searchCategories(query = query, client_id = Constants.ACCESS_KEY, page = page)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}