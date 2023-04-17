package com.lp4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lp4.R
import com.lp4.model.Personagem

class PersonagemListAdapter()  : RecyclerView.Adapter<PersonagemViewHolder>()
{

    private val personagemList : MutableList<Personagem> = mutableListOf();

    fun setItems(personagens: List<Personagem>){
        personagemList.clear()
        personagemList.addAll(personagens)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonagemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)
        return PersonagemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonagemViewHolder, position: Int) {
        val personagem = personagemList[position]
        holder.bind(personagem)

        Glide.with(holder.itemView.context)
            .load(personagem.image)
            .into(holder.heroImageView)
    }


    override fun getItemCount() = personagemList.size
}

class PersonagemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
    private val emailTextView: TextView = itemView.findViewById(R.id.description_text_view)
    val heroImageView: ImageView = itemView.findViewById(R.id.hero_image)

    fun bind(personagem: Personagem) {
        nameTextView.text = personagem.name
        emailTextView.text = personagem.description
    }
}
