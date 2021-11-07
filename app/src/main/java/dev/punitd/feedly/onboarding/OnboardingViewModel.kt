package dev.punitd.feedly.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.punitd.data.Channel
import dev.punitd.domain.usecase.GetPrefilledChannelsUseCase
import dev.punitd.domain.usecase.GetSavedChannelsUseCase
import dev.punitd.domain.usecase.SaveChannelUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getSavedChannelsUseCase: GetSavedChannelsUseCase,
    private val getPrefilledChannelsUseCase: GetPrefilledChannelsUseCase,
    private val saveChannelUseCase: SaveChannelUseCase,
) : ViewModel() {

    private val preFilledChannels: MutableStateFlow<List<Channel>> = MutableStateFlow(emptyList())

    fun bindPreFilledChannels() = preFilledChannels as Flow<List<Channel>>

    suspend fun getSavedChannels() = getSavedChannelsUseCase.execute()

    fun getPrefilledChannels() {
        viewModelScope.launch {
            val channels = getPrefilledChannelsUseCase.execute()
            preFilledChannels.value = channels
        }
    }

    fun selectChannel(channel: Channel) {
        viewModelScope.launch {
            val updatedChannels = preFilledChannels.value.map {
                if (it.title == channel.title) {
                    it.copy(isSelected = true)
                } else {
                    it
                }
            }
            preFilledChannels.value = updatedChannels
            saveChannelUseCase.execute(channel)
        }
    }
}
