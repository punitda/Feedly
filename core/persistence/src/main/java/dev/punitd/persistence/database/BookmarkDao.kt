package dev.punitd.persistence.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarkedarticle ORDER BY created_at ASC")
    fun getAllBookmarks(): Flow<List<BookmarkedArticle>>

    @Query("SELECT * FROM bookmarkedarticle WHERE guid = :id")
    fun getBookmarkById(id: String): List<BookmarkedArticle>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(bookmarkedArticle: BookmarkedArticle)
}
