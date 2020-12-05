package ru.flethy.androidacademyassignments.domain

import ru.flethy.androidacademyassignments.R
import ru.flethy.androidacademyassignments.model.Actor

class ActorsDataSource {
    fun getActors(): List<Actor> {
        return listOf(
                Actor(1, "Robert Downey Jr.", R.drawable.robert_downey_jr),
                Actor(2, "Chris Evans", R.drawable.chris_evans),
                Actor(3, "Mark Ruffalo", R.drawable.mark_ruffalo),
                Actor(4, "Chris Hemsworth", R.drawable.chris_hemsworth)
        )
    }
}