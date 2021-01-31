package ru.flethy.androidacademyassignments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ru.flethy.androidacademyassignments.model.Actor

class ActorsAdapter(): RecyclerView.Adapter<ActorsViewHolder>() {

    private var actors: List<Actor> = emptyList()

    fun bindActors(actorsList: List<Actor>) {
        actors = actorsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
        viewHolder.layoutParams.width = parent.measuredWidth / SPAN_COUNT
        return ActorsViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    companion object {
        const val SPAN_COUNT = 4
    }

}

class ActorsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val actorImage: ImageView = itemView.findViewById(R.id.actor)
    private val actorName: TextView = itemView.findViewById(R.id.actor_name)

    fun onBind(actor: Actor) {
        actorName.text = actor.name

        actorImage.clipToOutline = true
        actorImage.load(actor.imageUrl)
    }

}