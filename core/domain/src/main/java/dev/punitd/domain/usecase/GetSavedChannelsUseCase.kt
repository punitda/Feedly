package dev.punitd.domain.usecase

import dev.punitd.data.Channel
import dev.punitd.domain.mapper.ChannelDtoToChannel
import dev.punitd.domain.repository.ChannelsRepository
import javax.inject.Inject

interface GetSavedChannelsUseCase {
    suspend fun execute(): List<Channel>
}

class GetSavedChannelsUseCaseImpl @Inject constructor(
    private val channelsRepository: ChannelsRepository,
    private val mapper: ChannelDtoToChannel
) : GetSavedChannelsUseCase {
    override suspend fun execute(): List<Channel> {
        return channelsRepository.getSavedChannels().map { mapper.map(it) }
    }
}
