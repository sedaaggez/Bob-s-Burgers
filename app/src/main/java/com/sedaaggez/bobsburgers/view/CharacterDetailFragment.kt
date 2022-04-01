package com.sedaaggez.bobsburgers.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sedaaggez.bobsburgers.R
import com.sedaaggez.bobsburgers.databinding.FragmentCharacterDetailBinding

class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    private var fragmentBinding: FragmentCharacterDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCharacterDetailBinding.bind(view)
        fragmentBinding = binding
    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }
}