package dev.punitd.feedly.onboarding.domain.usecase

import dev.punitd.data.Channel
import dev.punitd.feedly.onboarding.domain.mapper.ChannelToChannelDto
import dev.punitd.persistence.database.AppDatabase
import javax.inject.Inject

class SaveChannelUseCaseImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val mapper: ChannelToChannelDto
) : SaveChannelUseCase {

    override suspend fun execute(channel: Channel) {
        val channelDto = mapper.map(channel)
        appDatabase.channelDao().insertChannel(channelDto)
    }
}
