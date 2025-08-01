package com.example.eni_shop.bo

import java.util.Date
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Article (
    @PrimaryKey
    var id : Long = 0,
    var name : String="",
    var description : String="",
    var price : Double=0,
    var urlImage: String="",
    var category: String="",
    //@Ignore
    var date: Date

)
