package com.sedaaggez.bobsburgers.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class CharacterFragmentFactory  @Inject constructor(
    private val glide: RequestManager
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            CharacterDetailFragment::class.java.name -> CharacterDetailFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }


}