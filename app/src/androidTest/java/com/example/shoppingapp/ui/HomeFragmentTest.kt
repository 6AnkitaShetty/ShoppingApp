package com.example.shoppingapp.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.shoppingapp.features.home.presentation.MainActivity
import com.example.shoppingapp.features.home.presentation.home.HomeFragment
import com.example.shoppingapp.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.example.shoppingapp.R

@ExperimentalCoroutinesApi
@HiltAndroidTest
class HomeFragmentTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_isProductsVisibleOnAppLaunch() {
        launchFragmentInHiltContainer<HomeFragment>()
        Espresso.onView(withId(R.id.productsRecycler)).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE
                )
            )
        )
    }
}