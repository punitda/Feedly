package dev.punitd.adp.domain.usecase

import dev.punitd.data.Article

interface BookmarkArticleUseCase {
    suspend fun execute(article: Article): Unit
}
