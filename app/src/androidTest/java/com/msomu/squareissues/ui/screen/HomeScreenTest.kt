package com.msomu.squareissues.ui.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.msomu.squareissues.MainActivity
import com.msomu.squareissues.R
import com.msomu.squareissues.ui.theme.SquareOkhttpIssuesTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testHeader(){
        composeTestRule.setContent {
            SquareOkhttpIssuesTheme {
                HomeScreen{}
            }
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.app_name)).assertIsDisplayed()
    }
}