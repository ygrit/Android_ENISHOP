package com.example.eni_shop.repository


import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import com.example.eni_shop.dao.DaoType

class ArticleRepository(
    private val articleDAOMemoryImpl: ArticleDAO,
    private val articleDAORoomImpl : ArticleDAO
) {

    private fun getDao(type: DaoType) = when(type){
        DaoType.MEMORY -> articleDAOMemoryImpl
        else -> articleDAORoomImpl
    }

    suspend fun getArticle(id: Long, daoType: DaoType = DaoType.MEMORY): Article? {
        return getDao(daoType).findById(id)
    }

    suspend fun addArticle(article: Article, daoType: DaoType = DaoType.MEMORY): Long {
        return getDao(daoType).insert(article)
    }

    suspend fun getAllArticles(daoType: DaoType = DaoType.MEMORY): List<Article> {
        return getDao(daoType).findAll()
    }

    suspend fun deleteArticle(article: Article, daoType: DaoType = DaoType.MEMORY){
        return getDao(daoType).delete(article)
    }
}