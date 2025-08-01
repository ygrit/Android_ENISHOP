package com.example.eni_shop.vm

import com.example.eni_shop.AppDatabase



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.DaoFactory
import com.example.eni_shop.dao.DaoType
import com.example.eni_shop.db.AppDatabase
import com.example.eni_shop.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticleDetailViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    private val _article = MutableStateFlow<Article?>(null)
    val article: StateFlow<Article?> = _article.asStateFlow()

    private val _isFav = MutableStateFlow<Boolean>(false)
    val isFav = _isFav.asStateFlow()

    fun loadArticle(articleId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _article.value = articleRepository.getArticle(articleId)
            val articleFav = articleRepository.getArticle(articleId, DaoType.ROOM)
            if (articleFav != null) {
                _isFav.value = true
            }
        }
    }

    fun addArticleToFav() {
        viewModelScope.launch(Dispatchers.IO) {
            _article.value?.let { articleRepository.addArticle(it, DaoType.ROOM) }
            _isFav.value = true
        }
    }

    fun removeArticleToFav() {
        viewModelScope.launch(Dispatchers.IO) {
            _article.value?.let { articleRepository.deleteArticle(it, DaoType.ROOM) }
            _isFav.value = false
        }
    }


    // Define ViewModel factory in a companion object
    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
//                // Create a SavedStateHandle for this ViewModel from extras
//                val savedStateHandle = extras.createSavedStateHandle()

                return ArticleDetailViewModel(
                    ArticleRepository(
                        articleDAOMemoryImpl = DaoFactory.createArticleDAO(DaoType.MEMORY),
                        articleDAORoomImpl = AppDatabase.getInstance(application.applicationContext)
                            .getArticleDAO()
                    )
                ) as T
            }
        }
    }

}