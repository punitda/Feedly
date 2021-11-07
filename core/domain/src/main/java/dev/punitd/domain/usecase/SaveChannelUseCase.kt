package dev.punitd.domain.usecase

import dev.punitd.data.Channel
import dev.punitd.domain.mapper.ChannelToChannelDto
import dev.punitd.domain.repository.ChannelsRepository
import javax.inject.Inject

interface SaveChannelUseCase {
    suspend fun execute(channel: Channel)
}

class SaveChannelUseCaseImpl @Inject constructor(
    private val channelsRepository: ChannelsRepository,
    private val mapper: ChannelToChannelDto
) : SaveChannelUseCase {

    override suspend fun execute(channel: Channel) {
        val channelDto = mapper.map(channel)
        channelsRepository.saveChannel(channelDto)
    }
}
