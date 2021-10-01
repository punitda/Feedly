package dev.punitd.bookmark

import com.airbnb.epoxy.TypedEpoxyController
import dev.punitd.data.Article
import dev.punitd.ui.components.view.articleItemView

class BookmarksController(
    private val adapterCallbacks: AdapterCallbacks
) : TypedEpoxyController<List<Article>>() {

    interface AdapterCallbacks {
        fun onArticleClicked(article: Article)
    }

    override fun buildModels(articles: List<Article>) {
        if (articles.isNotEmpty()) {
            articles.forEach {
                articleItemView {
                    id(it.guid)
                    article(it)
                    clickListener { model, _, _, _ ->
                        this@BookmarksController
                            .adapterCallbacks
                            .onArticleClicked(model.article())
                    }
                }
            }
        }
    }
}
