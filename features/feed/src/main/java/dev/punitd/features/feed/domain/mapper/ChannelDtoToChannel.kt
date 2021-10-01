package dev.punitd.features.feed.domain.mapper

import dev.punitd.base.Mapper
import dev.punitd.persistence.database.Channel
import javax.inject.Inject

class ChannelDtoToChannel @Inject constructor() : Mapper<Channel, dev.punitd.data.Channel> {
    override suspend fun map(from: Channel): dev.punitd.data.Channel {
        return dev.punitd.data.Channel(
            title = from.title,
            link = from.link,
        )
    }
}
