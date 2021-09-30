package dev.punitd.rss.parser.domain.usecase

import dev.punitd.base.data.Result
import dev.punitd.data.Channel

interface GetChannelsListUseCase {
    suspend fun execute(): Result<List<Channel>>
}
