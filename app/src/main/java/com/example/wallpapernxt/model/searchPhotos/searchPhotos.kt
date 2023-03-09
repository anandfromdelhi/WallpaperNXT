package com.example.wallpapernxt.model.searchPhotos

data class searchPhotos(
    val results: List<Result>,
    val total: Int,
    val total_pages: Int
)