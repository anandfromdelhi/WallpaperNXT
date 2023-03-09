package com.example.wallpapernxt.model.photoId

import com.example.wallpapernxt.model.searchCategories.Urls

data class CoverPhoto(
    val alt_description: String,
    val blur_hash: String,
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any>,
    val description: String,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val promoted_at: Any,
    val sponsorship: Any,
    val topic_submissions: TopicSubmissions,
    val updated_at: String,
    val user: User,
    val width: Int
)