package com.example.wallpapernxt.model.searchCategories

data class searchCategories(
    val results: List<Result>,
    val total: Int,
    val total_pages: Int
)