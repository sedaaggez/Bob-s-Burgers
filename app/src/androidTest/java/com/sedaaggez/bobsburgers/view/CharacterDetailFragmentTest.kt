package com.sedaaggez.bobsburgers.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.test.espresso.Espresso
import androidx.test.filters.MediumTest
import com.sedaaggez.bobsburgers.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class CharacterDetailFragmentTest {

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
    fun testNavigationFromCharacterDetailFragmentToCharactersFragment() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<CharacterDetailFragment>(factory = fragmentFactory) {
            androidx.navigation.Navigation.setViewNavController(requireView(), navController)
        }

        Espresso.pressBack()

        Mockito.verify(navController).popBackStack()
    }
}