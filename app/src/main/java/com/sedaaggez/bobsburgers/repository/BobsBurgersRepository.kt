package com.sedaaggez.bobsburgers.repository

import com.sedaaggez.bobsburgers.api.RetrofitAPI
import com.sedaaggez.bobsburgers.model.Character
import com.sedaaggez.bobsburgers.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BobsBurgersRepository @Inject constructor(
    private val retrofitApi: RetrofitAPI
) : BobsBurgersRepositoryInterface {

    override suspend fun getCharacters(): Flow<Resource<List<Character>>> = flow {
        val response = retrofitApi.getCharacters()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Resource.success(it))
            } ?: emit(Resource.error("Error", null))
        } else {
            emit(Resource.error("Error", null))
        }
    }

}