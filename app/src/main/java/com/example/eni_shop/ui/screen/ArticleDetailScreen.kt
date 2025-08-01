package com.example.eni_shop.ui.screen

import android.app.SearchManager
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.eni_shop.bo.Article
import com.example.eni_shop.repository.ArticleRepository
import com.example.eni_shop.ui.common.EniShopTopBar
import com.example.eni_shop.utils.toFrenchFormat
import com.example.eni_shop.utils.toPriceFormat
import com.example.eni_shop.vm.ArticleDetailViewModel

@Composable
fun ArticleDetailScreen(
    id: Long,
    navigationIcon : @Composable () -> Unit = {},
    articleDetailViewModel: ArticleDetailViewModel = viewModel(factory = ArticleDetailViewModel.Factory),
    modifier: Modifier = Modifier
) {

    val article by articleDetailViewModel.article.collectAsState()

    LaunchedEffect(id) {
        articleDetailViewModel.loadArticle(id)
    }

    Scaffold(
        topBar = { EniShopTopBar(navigationIcon = navigationIcon) }
    ) { paddingValues ->
        article?.let {
            ArticleDetail(article = it, modifier = Modifier.padding(paddingValues))
        }
    }
}


@Composable
fun ArticleDetail(article: Article, modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = article.name,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Justify,
            modifier = Modifier.testTag("articleTitle")
                .clickable {
//                Intent(Intent.ACTION_WEB_SEARCH).also {
//                    it.putExtra(SearchManager.QUERY, "${article.name} eni shop")
//                    context.startActivity(it)
//                }

                    Intent(
                        Intent.ACTION_VIEW,
                        "https://www.google.com/search?q=${article.name}+eni+shop".toUri()
                    ).also {
                        context.startActivity(it)
                    }
                }
        )

        Surface(
            color = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = article.urlImage,
                contentDescription = article.name,
                modifier = Modifier
                    .size(350.dp)
            )
        }
        Text(
            text = article.description,
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyMedium
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Prix ${article.price.toPriceFormat()} €"
            )
            Text(
                text = "Date de sortie : ${article.date.toFrenchFormat()}"
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Checkbox(
                checked = false, onCheckedChange = {}
            )
            Text(text = "Favoris ?")
        }
    }
}


@Preview
@Composable
private fun Preview() {
    ArticleDetailScreen(id = 2)
}
