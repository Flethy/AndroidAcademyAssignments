package ru.flethy.androidacademyassignments.model

import java.io.Serializable

data class Movie(
    val id: Int,
    val title: String,
    val pgAge: Int,
    val genres: List<Genre>,
    val reviewCount: Int,
    val runningTime: Int,
    val rating: Int,
    val imageUrl: String,
    val detailImageUrl: String,
    val storyLine: String,
    val actors: List<Actor>,
    val isLiked: Boolean
) : Serializable