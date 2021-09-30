package dev.punitd.rss.parser.domain.usecase

import dev.punitd.base.data.Result
import dev.punitd.base.data.Success
import dev.punitd.data.Channel
import javax.inject.Inject

class GetChannelsListUseCaseImpl @Inject constructor() : GetChannelsListUseCase {

    override suspend fun execute(): Result<List<Channel>> {
        return Success(
            data = listOf(
                Channel(
                    title = "BackChannel",
                    link = "https://medium.com/feed/backchannel",
                ),
                Channel(
                    title = "The Economist",
                    link = "https://medium.com/feed/the-economist",
                ),
                Channel(
                    title = "Matter",
                    link = "https://medium.com/feed/matter",
                ),
            )
        )
    }
}
