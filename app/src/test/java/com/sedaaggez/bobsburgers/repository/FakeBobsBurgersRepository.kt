package com.sedaaggez.bobsburgers.repository

import com.sedaaggez.bobsburgers.model.Character
import com.sedaaggez.bobsburgers.model.Relative
import com.sedaaggez.bobsburgers.util.Resource

class FakeBobsBurgersRepository : BobsBurgersRepositoryInterface {
    override suspend fun getCharacters(): Resource<List<Character>> {
        return Resource.success(
            listOf(
                Character(
                    "13-14",
                    "The Belchies",
                    "Male",
                    "",
                    506,
                    "",
                    "Zeke",
                    "",
                    listOf(
                        Relative(
                            "",
                            "",
                            ""
                        )
                    ),
                    "",
                    "",
                    ""
                )
            )
        )
    }

    override suspend fun getCharacter(id: Int): Resource<Character> {
        return Resource.success(
            Character(
                "13-14",
                "The Belchies",
                "Male",
                "",
                506,
                "",
                "Zeke",
                "",
                listOf(
                    Relative(
                        "",
                        "",
                        ""
                    )
                ),
                "",
                "",
                ""
            )
        )
    }
}