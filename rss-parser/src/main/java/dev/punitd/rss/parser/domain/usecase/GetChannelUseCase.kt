package dev.punitd.rss.parser.domain.usecase

import dev.punitd.base.data.Result
import dev.punitd.data.Channel

interface GetChannelUseCase {
    suspend fun execute(url: String): Result<Channel>
}
