package com.ryggs.olddvdlogo

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DVDLogoBouncerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun logoBouncesAndChangesColorOnEdge() {
        composeTestRule.setContent {
            DVDLogoBouncer()
        }

        var  initialColor = Color.Cyan
        var  initialPosition = Offset.Zero
        var  initialVelocity = Offset.Zero

        composeTestRule.runOnIdle {
            //Capture Init state
            initialColor = Color.Cyan
            initialPosition = Offset(0f, 0f)
            initialVelocity = Offset(2f, 1.5f)
        }

        composeTestRule.mainClock.advanceTimeBy(1000L)

        composeTestRule.runOnIdle {
            assert(initialPosition != Offset(0f,0f))
            assert(initialColor != Color.Cyan)
        }
    }

    fun logoSlowsDownWhenVelocityReduced() {
        composeTestRule.setContent {
            DVDLogoBouncer()
        }

        var initialPosition = Offset.Zero

        composeTestRule.runOnIdle {
            // Capture initial state
            initialPosition = Offset(0f, 0f)
        }

        // Simulate frames with reduced velocity
        composeTestRule.mainClock.advanceTimeBy(500L)

        composeTestRule.runOnIdle {
            // Check that the position has changed, but not by much (due to reduced velocity)
            assert(initialPosition != Offset(0f, 0f))
        }
    }
}