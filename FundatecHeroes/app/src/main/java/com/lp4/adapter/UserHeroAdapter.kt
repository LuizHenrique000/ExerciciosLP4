package com.lp4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lp4.R
import com.lp4.model.Hero

class HeroListAdapter()  : RecyclerView.Adapter<UserViewHolder>()
{

    private val heroList: MutableList<Hero> = mutableListOf();

    fun setItems(heros: List<Hero>){
        heroList.clear()
        heroList.addAll(heros)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val hero = heroList[position]
        holder.bind(hero)
    }

    override fun getItemCount() = heroList.size
}

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

    fun bind(hero: Hero) {
        nameTextView.text = hero.name
        descriptionTextView.text = hero.description
    }
}
