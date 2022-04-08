package com.sedaaggez.bobsburgers.repository

import com.sedaaggez.bobsburgers.model.Character
import com.sedaaggez.bobsburgers.util.Resource
import kotlinx.coroutines.flow.Flow

interface BobsBurgersRepositoryInterface {
    suspend fun getCharacters(): Flow<Resource<List<Character>>>
}