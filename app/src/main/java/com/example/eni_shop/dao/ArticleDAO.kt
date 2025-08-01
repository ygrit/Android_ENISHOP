package com.example.eni_shop.dao
package com.example.eni_shop.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.eni_shop.bo.Article

@Dao
interface ArticleDAO {

    @Query("SELECT * FROM Article WHERE id = :id")
    suspend fun findById(id : Long) : Article?

    @Insert
    suspend fun insert(article: Article) : Long

    @Query("SELECT * FROM Article")
    suspend fun findAll() : List<Article>

    @Delete
    suspend fun delete(article: Article)

}