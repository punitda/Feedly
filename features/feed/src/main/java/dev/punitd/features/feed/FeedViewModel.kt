package dev.punitd.features.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.punitd.base.data.Error
import dev.punitd.base.data.Success
import dev.punitd.rss.parser.domain.usecase.GetChannelUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val channelUseCase: GetChannelUseCase
) : ViewModel() {

    private val _viewState: MutableStateFlow<FeedViewState> =
        MutableStateFlow(FeedViewState.Loading)
    val viewState: Flow<FeedViewState> = _viewState

    fun fetchArticles(url: String) {
        viewModelScope.launch {
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

    private fun setState(reducer: FeedViewState.() -> FeedViewState) {
        val newState = _viewState.value.reducer()
        _viewState.value = newState
    }
}
