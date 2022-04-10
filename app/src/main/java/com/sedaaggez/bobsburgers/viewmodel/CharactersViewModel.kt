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
class CharactersViewModel @Inject constructor(
    private val repository: BobsBurgersRepositoryInterface
) : ViewModel() {

    private val _characterItems = MutableLiveData<Resource<List<Character>>>()
    val characterItemList: LiveData<Resource<List<Character>>> get() = _characterItems

    fun getCharacters() {
        _characterItems.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getCharacters()
            _characterItems.value = response
        }
    }
}