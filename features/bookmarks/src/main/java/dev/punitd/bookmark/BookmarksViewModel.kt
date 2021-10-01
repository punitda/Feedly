package dev.punitd.bookmark

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.punitd.bookmark.domain.usecase.GetBookmarksUseCase
import dev.punitd.data.Article
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val getBookmarksUseCase: GetBookmarksUseCase,
) : ViewModel() {

    suspend fun subscribeToBookmarks(): Flow<List<Article>> = getBookmarksUseCase.execute()
}
