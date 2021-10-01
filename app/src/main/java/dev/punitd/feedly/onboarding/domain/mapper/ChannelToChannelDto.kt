package dev.punitd.feedly.onboarding.domain.mapper

import dev.punitd.base.Mapper
import dev.punitd.persistence.database.Channel
import javax.inject.Inject

class ChannelToChannelDto @Inject constructor() : Mapper<dev.punitd.data.Channel, Channel> {
    override suspend fun map(from: dev.punitd.data.Channel): Channel {
        return Channel(
            title = from.title,
            link = from.link,
            isSelected = false
        )
    }
}
