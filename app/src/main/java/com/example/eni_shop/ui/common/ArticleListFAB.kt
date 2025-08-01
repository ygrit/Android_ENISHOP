package com.example.eni_shop.ui.common

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ArticleListFAB(
    onNavigateToArticleAdd : ()-> Unit,
    modifier: Modifier) {


    FloatingActionButton(
        onClick = {},
        shape = CircleShape,


    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Ajouter un article")

    }
}
