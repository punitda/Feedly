package dev.punitd.domain.repository

import dev.punitd.persistence.database.AppDatabase
import dev.punitd.persistence.database.Channel
import javax.inject.Inject

interface ChannelsRepository {
    suspend fun getSavedChannels(): List<Channel>
    suspend fun saveChannel(channel: Channel)
}

class ChannelsRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
) : ChannelsRepository {

    override suspend fun getSavedChannels(): List<Channel> {
        return appDatabase.channelDao().getAllChannelsOnce()
    }

    override suspend fun saveChannel(channel: Channel) {
        return appDatabase.channelDao().insertChannel(channel)
    }
}
