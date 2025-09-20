package com.example.furnishioz.ui.screen.search

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.furnishioz.R
import com.example.furnishioz.onNodeWithStringId
import com.example.furnishioz.ui.theme.FurnishiozTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchScreenKtTest{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup(){
        composeTestRule.setContent {
            FurnishiozTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                SearchScreen(navigateToDetail = { navController })
            }
        }
    }

    @Test
    fun searchProduct_shouldShowResult(){
        composeTestRule.onNodeWithStringId(R.string.search_placeholder).performTextInput("White Bowl")
        composeTestRule.onNodeWithText("White Bowl").assertIsDisplayed()
    }

    @Test
    fun searchProduct_shouldEmptyProduct(){
        composeTestRule.onNodeWithStringId(R.string.search_placeholder).performTextInput("dadadaf")
        composeTestRule.onNodeWithTag("empty_data").assertIsDisplayed()
    }

}