package dev.punitd.adp.domain.usecase

import dev.punitd.adp.domain.mapper.ArticleToBookmarkedArticle
import dev.punitd.data.Article
import dev.punitd.persistence.database.AppDatabase
import javax.inject.Inject

class BookmarkArticleUseCaseImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val mapper: ArticleToBookmarkedArticle,
) : BookmarkArticleUseCase {

    override suspend fun execute(article: Article) {
        val bookmarkedArticle = mapper.map(article)
        appDatabase.bookmarkDao().insertBookmark(bookmarkedArticle)
    }
}
