package com.example.rickmortygson.ui

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.rickmortygson.R
import com.example.rickmortygson.model.Character
import com.example.rickmortygson.databinding.CharacterItemBinding

class CharacterViewHolder(
    private val item: CharacterItemBinding
) : ViewHolder(item.root) {

    fun bindToView(character: Character) {
        item.name.text = character.name
        item.namespecies.text = character.species
        item.image.load(character.image) {
            placeholder(R.drawable.user_placeholder)
            crossfade(true)
    }

}}
