package com.sedaaggez.bobsburgers.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sedaaggez.bobsburgers.R
import com.sedaaggez.bobsburgers.databinding.FragmentCharactersBinding

class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private var fragmentBinding: FragmentCharactersBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCharactersBinding.bind(view)
        fragmentBinding = binding

    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }
}