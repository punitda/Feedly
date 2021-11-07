package dev.punitd.domain.mapper

import dev.punitd.base.Mapper
import dev.punitd.data.Channel
import javax.inject.Inject

class ChannelToChannelDto @Inject constructor() :
    Mapper<Channel, dev.punitd.persistence.database.Channel> {
    override suspend fun map(from: Channel): dev.punitd.persistence.database.Channel {
        return dev.punitd.persistence.database.Channel(
            title = from.title,
            link = from.link,
            isSelected = false
        )
    }
}
