package dev.punitd.features.feed

import com.airbnb.epoxy.TypedEpoxyController
import dev.punitd.data.Article
import dev.punitd.ui.components.view.CategoryView_
import dev.punitd.ui.components.view.articleItemView

class FeedController : TypedEpoxyController<List<Article>>() {

    override fun buildModels(articles: List<Article>) {
        articles.forEach {
            articleItemView {
                id(it.guid)
                article(it)
                categories(
                    it.categories.map {
                        CategoryView_().id(it).category(it)
                    }
                )
            }
        }
    }
}
