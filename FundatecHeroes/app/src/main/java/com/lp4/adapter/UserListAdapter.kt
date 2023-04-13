package com.lp4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lp4.R
import com.lp4.api.User

class UserListAdapter()  : RecyclerView.Adapter<UserViewHolder>()
{

    private val userList: MutableList<User> = mutableListOf();

    fun setItems(users: List<User>){
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount() = userList.size
}

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    private val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)

    fun bind(user: User) {
        nameTextView.text = user.name
        emailTextView.text = user.email
    }
}
