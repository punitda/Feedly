package dev.punitd.domain.usecase

import dev.punitd.data.Channel
import javax.inject.Inject

interface GetPrefilledChannelsUseCase {
    suspend fun execute(): List<Channel>
}

class GetPrefilledChannelsUseCaseImpl @Inject constructor() : GetPrefilledChannelsUseCase {
    override suspend fun execute(): List<Channel> {
        return listOf(
            Channel(
                title = "BackChannel",
                link = "https://medium.com/feed/backchannel",
                isSelected = false,
            ),
            Channel(
                title = "The Economist",
                link = "https://medium.com/feed/the-economist",
                isSelected = false,
            ),
            Channel(
                title = "Matter",
                link = "https://medium.com/feed/matter",
                isSelected = false,
            ),
        )
    }
}
