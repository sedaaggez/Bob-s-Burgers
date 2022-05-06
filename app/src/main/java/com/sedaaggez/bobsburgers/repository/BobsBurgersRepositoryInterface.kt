package com.sedaaggez.bobsburgers.repository

import com.sedaaggez.bobsburgers.model.Character
import com.sedaaggez.bobsburgers.util.Resource

interface BobsBurgersRepositoryInterface {
    suspend fun getCharacters(): Resource<List<Character>>
    suspend fun getCharacter(id: Int): Resource<Character>
}