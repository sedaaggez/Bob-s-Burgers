package com.sedaaggez.bobsburgers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.sedaaggez.bobsburgers.databinding.CharacterRowBinding
import com.sedaaggez.bobsburgers.model.Character
import javax.inject.Inject

class CharacterAdapter @Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    class CharacterViewHolder(val binding: CharacterRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var onItemClickListener :((Character) -> Unit) ?= null


    private val diffUtil = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var characters: List<Character>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)

    }

    fun setOnItemClickListener(listener: (Character) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]



        with(holder) {
            binding.tvCharacterName.text = character.name
            binding.tvCharacterGender.text = character.gender
            binding.tvHairColor.text = character.hairColor
            glide.load(character.image).into(binding.ivCharacter)
            binding.root.setOnClickListener{
                onItemClickListener?.let {
                    it(character)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return characters.size
    }

}