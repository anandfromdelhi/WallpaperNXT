package com.example.wallpapernxt.model.photoId

data class Result(
    val cover_photo: CoverPhoto,
    val curated: Boolean,
    val description: Any,
    val featured: Boolean,
    val id: String,
    val last_collected_at: String,
    val links: LinksXXX,
    val preview_photos: List<PreviewPhoto>,
    val `private`: Boolean,
    val published_at: String,
    val share_key: String,
    val tags: List<Tag>,
    val title: String,
    val total_photos: Int,
    val updated_at: String,
    val user: UserXX
)