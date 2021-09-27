package dev.punitd.rss.parser.data.mapper

import com.prof.rssparser.Channel
import dev.punitd.base.Mapper
import dev.punitd.base.data.Error
import dev.punitd.base.data.Result
import dev.punitd.base.data.Success
import javax.inject.Inject

class ArticleDtoToArticle @Inject constructor() :
    Mapper<Result<Channel>, Result<dev.punitd.data.Channel>> {

    @Suppress("ComplexMethod")
    override suspend fun map(from: Result<Channel>): Result<dev.punitd.data.Channel> {
        return when (from) {
            is Error -> Error(from.throwable)
            is Success -> {
                val channel = dev.punitd.data.Channel(
                    title = from.data.title ?: "",
                    link = from.data.link ?: "",
                    description = from.data.description,
                    image = dev.punitd.data.Channel.Image(
                        title = from.data.image?.title ?: "",
                        url = from.data.image?.url ?: "",
                        link = from.data.image?.link ?: "",
                    ),
                )

                val articles = from.data.articles.map {
                    dev.punitd.data.Article(
                        guid = it.guid ?: "",
                        title = it.title ?: "",
                        author = it.author ?: "",
                        link = it.link ?: "",
                        pubDate = it.pubDate ?: "",
                        description = it.description ?: "",
                        content = it.content ?: "",
                        imageUrl = it.image ?: "",
                        categories = it.categories
                    )
                }
                Success(data = channel.copy(articles = articles))
            }
        }
    }
}
