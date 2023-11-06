package com.example.furnishioz.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.example.furnishioz.R
import com.example.furnishioz.model.OrderItem
import com.example.furnishioz.model.Product
import com.example.furnishioz.onNodeWithStringId
import com.example.furnishioz.ui.theme.FurnishiozTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailProductScreenKtTest {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeOrderProduct = OrderItem(
        product = Product(
            1,
            "Queen Bed",
            "https://raw.githubusercontent.com/cheftz/asset/main/bed2.jpg",
            120,
            "Nara Bed is Livien's newest product with a natural concept that emphasizes the natural and beautiful side. Made of quality material equipped with environmentally friendly sunflower oil finishing makes this product more elegant.",
            "Nara",
        ),
        count = 0
    )

    @Before
    fun setup() {
        composeTestRule.setContent {
            FurnishiozTheme {
                DetailContent(
                    imageUrl = fakeOrderProduct.product.image,
                    title = fakeOrderProduct.product.name,
                    basePrice = fakeOrderProduct.product.price,
                    description = fakeOrderProduct.product.description,
                    brand = fakeOrderProduct.product.brand,
                    count = fakeOrderProduct.count,
                    onBackClick = { },
                    onAddToCart = { }
                )
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun detail_content_isDisplayer(){
        composeTestRule.onNodeWithText(fakeOrderProduct.product.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.price,
                fakeOrderProduct.product.price
            )
        ).assertIsDisplayed()
    }

    @Test
    fun increase_product_button_enabled(){
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsNotEnabled()
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsDisplayed()
    }

    @Test
    fun increaseProduct_correctCounter(){
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick().performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("2"))
    }

    @Test
    fun decreaseProduct_stillZero(){
        composeTestRule.onNodeWithStringId(R.string.minus_symbol).performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("0"))
    }
}