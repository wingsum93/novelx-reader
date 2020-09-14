package com.ericho.example

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.test.KoinTest


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class NovelPageTest : KoinTest {

    @get:Rule
    var activityRule = ActivityTestRule(NovelActivity::class.java, false, false)

    @Before
    fun setUp() {
        loadKoinModules(appModule)
        activityRule.launchActivity(Intent())

    }

    @After
    fun tearDown() {
        unloadKoinModules(appModule)
    }

    @Test
    fun whenActivityStartsLoginIsDisplayed() {
        onView(withId(R.id.frameLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.left)).check(matches(isDisplayed()))
        onView(withId(R.id.right)).check(matches(isDisplayed()))
    }
}