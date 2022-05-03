package com.sedaaggez.bobsburgers.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.sedaaggez.bobsburgers.adapter.CharacterAdapter
import javax.inject.Inject

class CharacterFragmentFactory  @Inject constructor(
    private val glide: RequestManager,
    private val characterAdapter: CharacterAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            CharactersFragment::class.java.name -> CharactersFragment(characterAdapter)
            CharacterDetailFragment::class.java.name -> CharacterDetailFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }


}