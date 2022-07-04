package com.example.shoppingapp.ui

import com.example.shoppingapp.R
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.shoppingapp.features.login.presentation.LoginActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
class LoginActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val rule = ActivityScenarioRule(LoginActivity::class.java)

    @Before
    fun setUp() {
        hiltRule.inject()
        launchActivity<LoginActivity>()
    }

    @Test
    fun performLogin_launch_main_screen() {
        onView(ViewMatchers.withId(R.id.et_email))
            .perform(typeText("foo@example.com"), closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.et_password))
            .perform(typeText("hello1234"), closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.btn_sign_in))
            .perform(click())
        onView(ViewMatchers.withId(R.id.container)).check(matches(isDisplayed()))
    }
}