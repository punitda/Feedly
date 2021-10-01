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
    suspend fun getBookmarkById(id: String): List<BookmarkedArticle>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmarkedArticle: BookmarkedArticle)

    @Query("DELETE FROM bookmarkedarticle WHERE guid = :id")
    suspend fun removeBookmark(id: String)
}
