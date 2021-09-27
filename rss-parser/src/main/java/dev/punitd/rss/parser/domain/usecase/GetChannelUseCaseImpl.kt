package dev.punitd.rss.parser.domain.usecase

import dev.punitd.base.data.Result
import dev.punitd.data.Channel
import dev.punitd.rss.parser.data.mapper.ArticleDtoToArticle
import dev.punitd.rss.parser.domain.repository.RssRepository
import javax.inject.Inject

class GetChannelUseCaseImpl @Inject constructor(
    private val repository: RssRepository,
    private val mapper: ArticleDtoToArticle,
) : GetChannelUseCase {

    override suspend fun execute(url: String): Result<Channel> {
        val channel = repository.getChannel(url)
        return mapper.map(channel)
    }
}
