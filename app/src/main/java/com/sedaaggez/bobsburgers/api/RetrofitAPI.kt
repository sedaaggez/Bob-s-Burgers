package com.sedaaggez.bobsburgers.api

import com.sedaaggez.bobsburgers.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitAPI {

    @GET("/characters")
    suspend fun getCharacters() : Response<List<Character>>

    @GET("/characters/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ) : Response<Character>
}