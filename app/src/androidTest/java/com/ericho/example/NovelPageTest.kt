package com.ericho.example

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.ericho.example.ui.novel.NovelActivity
import io.mockk.mockkClass
import org.junit.After
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class NovelPageTest {

    @get:Rule
    var activityRule = ActivityTestRule(NovelActivity::class.java, false, false)

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        // Your KoinApplication instance here
        modules(appModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        // Your way to build a Mock here
        mockkClass(clazz)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun whenActivityStartsLoginIsDisplayed() {
//        startKoin {  }
        activityRule.launchActivity(Intent())

        onView(withId(R.id.frameLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.left)).check(matches(isDisplayed()))
        onView(withId(R.id.right)).check(matches(isDisplayed()))
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpAA() {


        }
    }
}