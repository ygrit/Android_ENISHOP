package com.example.eni_shop

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isToggleable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.eni_shop.bo.Article
import com.example.eni_shop.ui.screen.ArticleDetail
import org.junit.Rule
import org.junit.Test

class ArticleDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val mockArticle = Article(
        id = 1,
        name = "Article Test",
        description = "Description Test",
        price = 22.22
    )

    @Test
    fun articleDetailTextsIsDisplayed() {

        composeTestRule.setContent {
            ArticleDetail(article = mockArticle)
        }

        //test pour vérifier que l'affichage est ok
        composeTestRule
            .onNodeWithTag("articleTitle")
            .assertExists()
        composeTestRule
            .onNodeWithTag("articleTitle")
            .assertTextContains("Article Test")
            .assertHasClickAction()
        composeTestRule
            .onNodeWithText("Description Test")
        composeTestRule
            .onNodeWithText("22.22")
        composeTestRule
            .onNodeWithText("Favoris ?")
    }

    @Test
    fun articleDetailCheckboxIsNotChecked(){

        composeTestRule.setContent {
            ArticleDetail(article = mockArticle)
        }

        composeTestRule.onNode(isToggleable()).assertIsOff()

    }


}