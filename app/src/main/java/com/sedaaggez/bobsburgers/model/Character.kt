package com.sedaaggez.bobsburgers.model

import java.io.Serializable

data class Character(
    val age: String,
    val firstEpisode: String,
    val gender: String,
    val hairColor: String,
    val id: Int,
    val image: String,
    val name: String,
    val occupation: String,
    val relatives: List<String>,
    val url: String,
    val voicedBy: String
) : Serializable