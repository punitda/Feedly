package dev.punitd.features.feed

import com.airbnb.epoxy.TypedEpoxyController
import dev.punitd.data.Article
import dev.punitd.data.Channel
import dev.punitd.ui.components.view.articleItemView
import dev.punitd.ui.components.view.channelInfoView
import dev.punitd.ui.components.view.sectionHeader

class FeedController : TypedEpoxyController<Channel>() {

    override fun buildModels(channel: Channel?) {
        channel?.let {
            addChannelInfo(it)
            addArticles(it.articles)
        }
    }

    private fun addChannelInfo(channel: Channel) {
        channelInfoView {
            id(channel.title)
            channel(channel)
        }
    }

    private fun addArticles(articles: List<Article>) {
        if (articles.isNotEmpty()) {
            sectionHeader {
                id("ArticlesSectionHeader")
                titleRes(R.string.articles_section_header)
            }

            articles.forEach {
                articleItemView {
                    id(it.guid)
                    article(it)
                }
            }
        }
    }
}
