package com.example.eni_shop.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eni_shop.ui.common.NavigationBackIcon
import com.example.eni_shop.ui.screen.ArticleDetailScreen
import com.example.eni_shop.ui.screen.ArticleFormScreen
import com.example.eni_shop.ui.screen.ArticleListScreen

@Composable
fun EniShopNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navHostController,
        startDestination = ArticleListDestination.route
    ) {
        composable(route = ArticleListDestination.route) {
            ArticleListScreen(
                onNavigateToArticleDetail = { articleId ->
                    navHostController.navigate("${ArticleDetailDestination.route}/$articleId")
                },
                onNavigateToArticleAdd = {
                    navHostController.navigate(ArticleFormDestination.route)
                }
            )
        }
        composable(route = ArticleFormDestination.route) {
            ArticleFormScreen(
                navigationIcon = {
                    if (navHostController.previousBackStackEntry != null) {
                        NavigationBackIcon(
                            onNavigateToBack = {
                                navHostController.popBackStack()
                            }
                        )
                    }
                }
            )
        }
        composable(
            route = ArticleDetailDestination.routeWithArgs,
            arguments = ArticleDetailDestination.arguments
        ) {

            val articleId = it.arguments?.getLong(ArticleDetailDestination.argName)
            if (articleId != null) {
                ArticleDetailScreen(
                    id = articleId,
                    navigationIcon = {
                        if (navHostController.previousBackStackEntry != null) {
                            NavigationBackIcon(
                                onNavigateToBack = {
                                    navHostController.popBackStack()
                                }
                            )
                        }
                    }
                )
            }

        }
    }

}