package dev.punitd.features.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.punitd.base.data.Error
import dev.punitd.base.data.Success
import dev.punitd.data.Channel
import dev.punitd.domain.usecase.GetSavedChannelsUseCase
import dev.punitd.rss.parser.domain.usecase.GetChannelUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class FeedViewModel @Inject constructor(
    // Gets Article + Channel Info for selected channel
    private val channelUseCase: GetChannelUseCase,
    // Gets All Channels saved in local DB
    private val getSavedChannelsUseCase: GetSavedChannelsUseCase,
) : ViewModel() {

    private val viewState: MutableStateFlow<FeedViewState> = MutableStateFlow(FeedViewState.Loading)
    fun bind() = viewState as Flow<FeedViewState>

    private val channelsListState: MutableStateFlow<List<Channel>> = MutableStateFlow(emptyList())
    fun bindChannelsList() = channelsListState as Flow<List<Channel>>

    init {
        getSavedChannels()
    }

    fun switchChannel(channel: Channel) {
        // Only switch if channel selected is different
        if (channelsListState.value.find { it.isSelected }?.title != channel.title) {
            selectChannel(channel)
            fetchArticles(channel.link)
        }
    }

    fun refetchChannel() {
        val selectedChannel = channelsListState.value.find { it.isSelected }
        selectedChannel?.let { fetchArticles(it.link) }
    }

    private fun getSavedChannels() {
        viewModelScope.launch {
            val channels = getSavedChannelsUseCase.execute()
            channelsListState.value = channels

            // By default we will show first channel
            // added by user for first time screen is opened.
            val defaultChannel = channels.first()
            switchChannel(defaultChannel)
        }
    }

    private fun fetchArticles(url: String) {
        viewModelScope.launch {
            setState { FeedViewState.Loading }
            when (val result = channelUseCase.execute(url)) {
                is Error -> {
                    setState {
                        FeedViewState.Error(
                            result.throwable.message ?: "Unable to fetch feed. Please try again!"
                        )
                    }
                }
                is Success -> {
                    setState {
                        FeedViewState.Success(result.data)
                    }
                }
            }
        }
    }

    private fun selectChannel(channel: Channel) {
        val updatedChannels = channelsListState.value.map {
            if (it.title == channel.title) {
                it.copy(isSelected = true)
            } else {
                it.copy(isSelected = false)
            }
        }
        channelsListState.value = updatedChannels
    }

    private fun setState(reducer: FeedViewState.() -> FeedViewState) {
        val newState = viewState.value.reducer()
        viewState.value = newState
    }
}
