package dev.punitd.bookmark.domain.usecase

import dev.punitd.bookmark.domain.mapper.BookmarkedArticleToArticle
import dev.punitd.data.Article
import dev.punitd.persistence.database.AppDatabase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBookmarksUseCaseImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val mapper: BookmarkedArticleToArticle,
) : GetBookmarksUseCase {

    override suspend fun execute(): Flow<List<Article>> {
        return appDatabase.bookmarkDao().getAllBookmarks()
            .map {
                it.map { article -> mapper.map(article) }
            }
    }
}
