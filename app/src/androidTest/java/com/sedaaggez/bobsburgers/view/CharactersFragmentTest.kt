package com.sedaaggez.bobsburgers.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.sedaaggez.bobsburgers.adapter.CharacterAdapter
import com.sedaaggez.bobsburgers.launchFragmentInHiltContainer
import com.sedaaggez.bobsburgers.model.Character
import com.sedaaggez.bobsburgers.model.Relative
import com.sedaaggez.bobsburgers.repository.FakeBobsBurgersRepository
import com.sedaaggez.bobsburgers.viewmodel.CharactersViewModel
import com.sedaaggez.bobsburgers.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class CharactersFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: CharacterFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromCharactersFragmentToCharacterDetailFragment() {
        val navController = Mockito.mock(NavController::class.java)
        val character = Character(
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
        val testViewModel = CharactersViewModel(FakeBobsBurgersRepository())
        lateinit var adapter: CharacterAdapter

        launchFragmentInHiltContainer<CharactersFragment>(factory = fragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)
            viewModel = testViewModel
            adapter = characterAdapter
        }

        runBlocking {
            adapter.characters = (
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



        Espresso.onView(withId(R.id.rvCharacters)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CharacterAdapter.CharacterViewHolder>(
                0, ViewActions.click()
            )
        )

        Mockito.verify(navController).navigate(
            CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(character.id)
        )
    }

}