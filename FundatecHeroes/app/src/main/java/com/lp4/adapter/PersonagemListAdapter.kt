package com.lp4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lp4.R
import com.lp4.model.Character

class PersonagemListAdapter()  : RecyclerView.Adapter<PersonagemViewHolder>()
{

    private val characterList : MutableList<Character> = mutableListOf();

    fun setItems(personagens: List<Character>){
        characterList.clear()
        characterList.addAll(personagens)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonagemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)
        return PersonagemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonagemViewHolder, position: Int) {
        val personagem = characterList[position]
        holder.bind(personagem)

        Glide.with(holder.itemView.context)
            .load(personagem.image)
            .into(holder.heroImageView)
    }


    override fun getItemCount() = characterList.size
}

class PersonagemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
    private val emailTextView: TextView = itemView.findViewById(R.id.description_text_view)
    val heroImageView: ImageView = itemView.findViewById(R.id.hero_image)

    fun bind(character: Character) {
        nameTextView.text = character.name
        emailTextView.text = character.description
    }
}
