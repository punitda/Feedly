package dev.punitd.features.feed.domain.usecase

import dev.punitd.data.Channel
import dev.punitd.features.feed.domain.mapper.ChannelDtoToChannel
import dev.punitd.persistence.database.AppDatabase
import javax.inject.Inject
import kotlinx.coroutines.flow.map

class GetSavedChannelsUseCaseImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val mapper: ChannelDtoToChannel,
) : GetSavedChannelsUseCase {

    override suspend fun execute(): List<Channel> {
        return appDatabase.channelDao().getAllChannelsOnce().map { mapper.map(it) }
    }
}
