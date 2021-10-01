package dev.punitd.feedly.onboarding.domain.usecase

import dev.punitd.data.Channel
import dev.punitd.feedly.onboarding.domain.mapper.ChannelDtoToChannel
import dev.punitd.persistence.database.AppDatabase
import javax.inject.Inject

class GetSavedChannelsUseCaseImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val mapper: ChannelDtoToChannel,
) : GetSavedChannelsUseCase {

    override suspend fun execute(): List<Channel> {
        return appDatabase.channelDao().getAllChannelsOnce().map { mapper.map(it) }
    }
}
