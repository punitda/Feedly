package dev.punitd.adp.domain.usecase

import dev.punitd.persistence.database.AppDatabase
import javax.inject.Inject

class RemoveBookmarkedArticleUseCaseImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : RemoveBookmarkedArticleUseCase {
    override suspend fun execute(articleId: String) {
        appDatabase.bookmarkDao().removeBookmark(articleId)
    }
}
