package com.sedaaggez.bobsburgers.repository

import com.sedaaggez.bobsburgers.api.RetrofitAPI
import com.sedaaggez.bobsburgers.model.Character
import com.sedaaggez.bobsburgers.util.Resource
import javax.inject.Inject

class BobsBurgersRepository @Inject constructor(
    private val retrofitApi: RetrofitAPI
) : BobsBurgersRepositoryInterface {

    override suspend fun getCharacters(): Resource<List<Character>> {
        return try {
            val response = retrofitApi.getCharacters()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error")
            } else {
                Resource.error("Error")
            }

        } catch (e: Exception) {
            Resource.error("No data!")
        }
    }

    override suspend fun getCharacter(id: Int): Resource<Character> {
        return try {
            val response = retrofitApi.getCharacter(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error")
            } else {
                Resource.error("Error")
            }

        } catch (e: Exception) {
            Resource.error("No data!")
        }
    }
}