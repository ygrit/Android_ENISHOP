package com.example.eni_shop.dao

import com.example.eni_shop.dao.memory.ArticleDAOMemoryImpl

abstract class DaoFactory {

    companion object {

        fun createArticleDAO(type: DaoType): ArticleDAO {

            return when (type) {
                DaoType.MEMORY -> ArticleDAOMemoryImpl()
                DaoType.NETWORK -> TODO()
            }
        }
    }
}

