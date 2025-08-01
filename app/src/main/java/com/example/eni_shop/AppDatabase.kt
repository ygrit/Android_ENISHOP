package com.example.eni_shop


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import com.example.eni_shop.utils.DateConverter

@Database(version = 1, entities = [Article::class])
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    //lister ici toutes les daos de votre projet
    abstract fun getArticleDAO() : ArticleDAO

    companion object{

        private var instance : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase{

            if(instance == null){

                instance = Room.databaseBuilder(
                    context = context,
                    name = "eni_shop.db",
                    klass = AppDatabase::class.java
                ).build()
            }

            return instance as AppDatabase
        }


    }

}