package dev.punitd.persistence.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookmarkedArticle(
    @PrimaryKey val guid: String,
    @NonNull val title: String,
    @NonNull val author: String,
    @NonNull val link: String,
    @NonNull @ColumnInfo(name = "pub_date") val pubDate: String,
    @NonNull @ColumnInfo val content: String,
    val description: String?,
    @ColumnInfo(name = "image_url") val imageUrl: String?,
    @ColumnInfo(name = "created_at") val createdAt: Long
)
