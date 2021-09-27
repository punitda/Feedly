package dev.punitd.features.feed

import dev.punitd.data.Article

sealed class FeedViewState {
    object Loading : FeedViewState()
    data class Error(val message: String) : FeedViewState()
    data class Success(val articles: List<Article>) : FeedViewState()
}
