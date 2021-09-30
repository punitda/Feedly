package dev.punitd.features

import dev.punitd.data.Channel

sealed class ChannelsListViewState {
    object Initial : ChannelsListViewState()
    data class Error(val message: String) : ChannelsListViewState()
    data class Success(val channels: List<Channel>) : ChannelsListViewState()
}
