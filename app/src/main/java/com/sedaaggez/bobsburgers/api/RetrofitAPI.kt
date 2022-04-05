package com.sedaaggez.bobsburgers.api

import com.sedaaggez.bobsburgers.model.Character
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("/characters")
    suspend fun getCharacter() : Response<List<Character>>
}