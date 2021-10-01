package dev.punitd.adp.domain.usecase

interface RemoveBookmarkedArticleUseCase {
    suspend fun execute(articleId: String): Unit
}
