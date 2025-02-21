package com.example.rickmortygson.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortygson.databinding.CharacterItemBinding
import com.example.rickmortygson.model.Character

class CharacterAdapter : RecyclerView.Adapter<CharacterViewHolder>() {

    private val users = mutableListOf<Character>()

    fun loadUsers(data: List<Character>) {
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindToView(users[position])
    }
}