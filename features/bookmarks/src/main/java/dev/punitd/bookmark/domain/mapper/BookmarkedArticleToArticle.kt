package dev.punitd.bookmark.domain.mapper

import dev.punitd.base.Mapper
import dev.punitd.data.Article
import dev.punitd.persistence.database.BookmarkedArticle
import javax.inject.Inject

class BookmarkedArticleToArticle @Inject constructor() : Mapper<BookmarkedArticle, Article> {

    override suspend fun map(from: BookmarkedArticle): Article {
        return Article(
            guid = from.guid,
            title = from.title,
            author = from.author,
            link = from.link,
            pubDate = from.pubDate,
            description = from.description ?: "",
            content = from.content,
            imageUrl = from.imageUrl,
        )
    }
}
