package com.sedaaggez.bobsburgers.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.sedaaggez.bobsburgers.R
import com.sedaaggez.bobsburgers.databinding.FragmentCharacterDetailBinding
import javax.inject.Inject

class CharacterDetailFragment @Inject constructor(
    val glide: RequestManager
) : Fragment(R.layout.fragment_character_detail) {

    private var fragmentBinding: FragmentCharacterDetailBinding? = null
    private var characterId = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCharacterDetailBinding.bind(view)
        fragmentBinding = binding


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        arguments?.let {
            characterId = CharacterDetailFragmentArgs.fromBundle(it).characterId
        }
    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }
}