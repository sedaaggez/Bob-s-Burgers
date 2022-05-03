package com.sedaaggez.bobsburgers.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sedaaggez.bobsburgers.R
import com.sedaaggez.bobsburgers.adapter.CharacterAdapter
import com.sedaaggez.bobsburgers.databinding.FragmentCharactersBinding
import com.sedaaggez.bobsburgers.util.Status
import com.sedaaggez.bobsburgers.viewmodel.CharactersViewModel
import javax.inject.Inject

class CharactersFragment @Inject constructor(val characterAdapter: CharacterAdapter) : Fragment(R.layout.fragment_characters) {

    private var fragmentBinding: FragmentCharactersBinding? = null
    lateinit var viewModel: CharactersViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCharactersBinding.bind(view)
        fragmentBinding = binding

        viewModel = ViewModelProvider(requireActivity())[CharactersViewModel::class.java]

        viewModel.getCharacters()

        binding.rvCharacters.adapter = characterAdapter
        binding.rvCharacters.layoutManager = LinearLayoutManager(requireContext())

        subscribeToObservers()
    }


    private fun subscribeToObservers() {
        viewModel.characterItemList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    fragmentBinding?.progressBar?.visibility = View.GONE
                    characterAdapter.characters = it.data ?: listOf()
                }
                Status.ERROR -> {
                    fragmentBinding?.progressBar?.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()
                }
                Status.LOADING -> {
                    fragmentBinding?.progressBar?.visibility = View.VISIBLE

                }
            }
        })
    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }
}