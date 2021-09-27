package dev.punitd.features.feed

import dev.punitd.data.Channel

sealed class FeedViewState {
    object Loading : FeedViewState()
    data class Error(val message: String) : FeedViewState()
    data class Success(val channel: Channel) : FeedViewState()
}
