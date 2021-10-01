package dev.punitd.adp.domain.usecase

interface IsArticleBookmarkedUseCase {
    suspend fun execute(articleId: String): Boolean
}
