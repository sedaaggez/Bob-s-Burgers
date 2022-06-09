package com.sedaaggez.bobsburgers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedaaggez.bobsburgers.model.Character
import com.sedaaggez.bobsburgers.repository.BobsBurgersRepositoryInterface
import com.sedaaggez.bobsburgers.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: BobsBurgersRepositoryInterface
) : ViewModel() {

    private val _characterItem = MutableLiveData<Resource<Character>>()
    val character: LiveData<Resource<Character>> get() = _characterItem

    fun getCharacter(id: Int) {
        _characterItem.value = Resource.loading()
        viewModelScope.launch {
            val response = repository.getCharacter(id)
            _characterItem.value = response
        }
    }
}
