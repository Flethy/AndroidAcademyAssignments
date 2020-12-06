package ru.flethy.androidacademyassignments.model

data class Movie(
    val id: Int,
    val name: String,
    val age: Int,
    val genre: String,
    val reviewCount: Int,
    val duration: Int,
    val rating: Int,
    val poster: Int,
    val backgroundPoster: Int?,
    val storyline: String,
    val actors: List<Actor>?,
    val isLiked: Boolean,
)