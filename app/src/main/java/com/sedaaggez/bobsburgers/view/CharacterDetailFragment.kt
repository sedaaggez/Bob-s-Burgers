package com.sedaaggez.bobsburgers.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.sedaaggez.bobsburgers.R
import com.sedaaggez.bobsburgers.databinding.FragmentCharacterDetailBinding
import com.sedaaggez.bobsburgers.util.Status
import com.sedaaggez.bobsburgers.viewmodel.CharacterDetailViewModel
import com.sedaaggez.bobsburgers.viewmodel.CharactersViewModel
import javax.inject.Inject

class CharacterDetailFragment @Inject constructor(
    val glide: RequestManager
) : Fragment(R.layout.fragment_character_detail) {

    private var fragmentBinding: FragmentCharacterDetailBinding? = null
    private var characterId = 0

    lateinit var viewModel: CharacterDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCharacterDetailBinding.bind(view)
        fragmentBinding = binding

        viewModel = ViewModelProvider(requireActivity())[CharacterDetailViewModel::class.java]



        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        arguments?.let {
            characterId = CharacterDetailFragmentArgs.fromBundle(it).characterId
            viewModel.getCharacter(characterId)
        }

        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.character.observe(viewLifecycleOwner, Observer { character ->
            when (character.status) {
                Status.SUCCESS -> {
                    fragmentBinding?.pbDetail?.visibility = View.GONE
                    fragmentBinding?.tvCharacterDetailName?.text = character.data?.name ?: "No name"
                    fragmentBinding?.tvCharacterDetailGender?.text = character.data?.gender ?: "No gender"
                    fragmentBinding?.tvCharacterDetailHairColor?.text = character.data?.hairColor ?: "No hairColor"
                    fragmentBinding?.tvCharacterDetailOccupation?.text = character.data?.occupation ?: "No occupation"
                    fragmentBinding?.ivCharacterDetail?.let {
                        glide.load(character.data?.image).into(
                            it
                        )
                    }
                }
                Status.ERROR -> {
                    fragmentBinding?.pbDetail?.visibility = View.GONE
                    Toast.makeText(requireContext(), character.message ?: "Error", Toast.LENGTH_LONG)
                        .show()
                }
                Status.LOADING -> {
                    fragmentBinding?.pbDetail?.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }



}