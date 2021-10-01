package dev.punitd.adp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.punitd.adp.domain.usecase.BookmarkArticleUseCase
import dev.punitd.adp.domain.usecase.IsArticleBookmarkedUseCase
import dev.punitd.adp.domain.usecase.RemoveBookmarkedArticleUseCase
import dev.punitd.data.Article
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val isArticleBookmarkedUseCase: IsArticleBookmarkedUseCase,
    private val bookmarkArticleUseCase: BookmarkArticleUseCase,
    private val removeBookmarkedArticleUseCase: RemoveBookmarkedArticleUseCase,
) : ViewModel() {

    private val bookmarkedState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun bind() = bookmarkedState as Flow<Boolean>

    fun checkIfBookmarked(articleId: String) {
        viewModelScope.launch {
            val isBookmarked = isArticleBookmarkedUseCase.execute(articleId)
            bookmarkedState.value = isBookmarked
        }
    }

    fun toggleBookmark(article: Article) {
        viewModelScope.launch {
            if (bookmarkedState.value) {
                removeBookmarkedArticleUseCase.execute(article.guid)
            } else {
                bookmarkArticleUseCase.execute(article)
            }
            bookmarkedState.value = !bookmarkedState.value
        }
    }
}
