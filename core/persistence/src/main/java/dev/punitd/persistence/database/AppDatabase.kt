package dev.punitd.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookmarkedArticle::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}
