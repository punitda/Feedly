package dev.punitd.features.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.punitd.base.data.Error
import dev.punitd.base.data.Success
import dev.punitd.data.Channel
import dev.punitd.features.ChannelsListViewState
import dev.punitd.rss.parser.domain.usecase.GetChannelUseCase
import dev.punitd.rss.parser.domain.usecase.GetChannelsListUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val channelUseCase: GetChannelUseCase,
    private val channelsListUseCase: GetChannelsListUseCase,
) : ViewModel() {

    private val viewState: MutableStateFlow<FeedViewState> = MutableStateFlow(FeedViewState.Initial)
    private val channelsListState: MutableStateFlow<ChannelsListViewState> =
        MutableStateFlow(ChannelsListViewState.Initial)

    fun bind() = viewState as Flow<FeedViewState>
    fun bindChannelsList() = channelsListState as Flow<ChannelsListViewState>

    init {
        getFeedUrls()
    }

    fun getFeedUrls() {
        viewModelScope.launch {
            when (val result = channelsListUseCase.execute()) {
                is Error -> {
                    setChannelsState {
                        ChannelsListViewState.Error(
                            result.throwable.message
                                ?: "Unable to fetch list of feeds. Please try again!"
                        )
                    }
                }
                is Success -> {
                    setChannelsState {
                        ChannelsListViewState.Success(
                            channels = result.data,
                        )
                    }
                }
            }
        }
    }

    fun fetchArticles(url: String) {
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

    fun selectChannel(channel: Channel) {
        val currentState = (channelsListState.value as ChannelsListViewState.Success)
        val updatedChannels = currentState.channels.map {
            if (it.title == channel.title) {
                it.copy(isSelected = true)
            } else {
                it
            }
        }
        channelsListState.value = currentState.copy(channels = updatedChannels)
    }

    fun isChannelSelected(): Boolean {
        val channel = (channelsListState.value as? ChannelsListViewState.Success)
            ?.channels
            ?.firstOrNull { it.isSelected }
        return channel != null
    }

    private fun setState(reducer: FeedViewState.() -> FeedViewState) {
        val newState = viewState.value.reducer()
        viewState.value = newState
    }

    private fun setChannelsState(reducer: ChannelsListViewState.() -> ChannelsListViewState) {
        val newState = channelsListState.value.reducer()
        channelsListState.value = newState
    }
}
