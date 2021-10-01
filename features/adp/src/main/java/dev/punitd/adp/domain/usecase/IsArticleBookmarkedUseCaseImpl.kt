package dev.punitd.adp.domain.usecase

import dev.punitd.persistence.database.AppDatabase
import javax.inject.Inject

class IsArticleBookmarkedUseCaseImpl @Inject constructor(
    private val appDatabase: AppDatabase,
) : IsArticleBookmarkedUseCase {
    override suspend fun execute(articleId: String): Boolean {
        val articles = appDatabase.bookmarkDao().getBookmarkById(articleId)
        return articles.isNotEmpty()
    }
}
