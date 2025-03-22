package org.isoron.uhabits.acceptance

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.isoron.uhabits.BaseUserInterfaceTest
import org.isoron.uhabits.R
import org.isoron.uhabits.acceptance.steps.CommonSteps.launchApp
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ManuItemsTest: BaseUserInterfaceTest() {

    @Test
    @Throws(Exception::class)
    fun testActionCreateHabitIsDisplayed() {
        launchApp()
        Espresso.onView(ViewMatchers.withId(R.id.actionCreateHabit))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    @Throws(Exception::class)
    fun testActionFilterIsDisplayed() {
        launchApp()
        Espresso.onView(ViewMatchers.withId(R.id.action_filter))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    @Throws(Exception::class)
    fun testFilterItemsIsDisplayed() {
        launchApp()
        Espresso.onView(ViewMatchers.withId(R.id.action_filter))
            .perform(ViewActions.click())
    }
}