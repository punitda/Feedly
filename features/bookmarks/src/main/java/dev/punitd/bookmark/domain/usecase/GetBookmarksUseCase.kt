package dev.punitd.bookmark.domain.usecase

import dev.punitd.data.Article
import kotlinx.coroutines.flow.Flow

interface GetBookmarksUseCase {
    suspend fun execute(): Flow<List<Article>>
}
