package dev.punitd.adp.domain.mapper

import dev.punitd.base.Mapper
import dev.punitd.data.Article
import dev.punitd.persistence.database.BookmarkedArticle
import javax.inject.Inject

class ArticleToBookmarkedArticle @Inject constructor() : Mapper<Article, BookmarkedArticle> {
    override suspend fun map(from: Article): BookmarkedArticle {
        return BookmarkedArticle(
            guid = from.guid,
            title = from.title,
            author = from.author,
            link = from.link,
            pubDate = from.pubDate,
            content = from.content,
            description = from.description,
            imageUrl = from.imageUrl,
            createdAt = System.currentTimeMillis()
        )
    }
}
