package dev.punitd.ui.components.view

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.airbnb.epoxy.SimpleEpoxyController
import dev.punitd.data.Article
import dev.punitd.ui.components.R
import dev.punitd.ui.components.databinding.ViewArticleItemBinding
import dev.punitd.ui.components.insetOnlyCarouselPadding
import dev.punitd.ui.components.view.ArticleItemView.ArticleItemHolder

@EpoxyModelClass
abstract class ArticleItemView : EpoxyModelWithHolder<ArticleItemHolder>() {

    @EpoxyAttribute
    lateinit var article: Article

    @EpoxyAttribute
    lateinit var categories: List<CategoryView>

    override fun getDefaultLayout() = R.layout.view_article_item

    override fun bind(holder: ArticleItemHolder) {
        holder.binding.apply {
            article.imageUrl?.let {
                articleImage.visibility = View.VISIBLE
                articleImage.load(it)
            } ?: run {
                articleImage.visibility = View.GONE
            }
            articleTitle.text = article.title
            authorName.text = article.author
            pubDate.text = article.pubDate

            categoryCarousel.setModels(categories)
        }
    }

    class ArticleItemHolder : EpoxyHolder() {
        lateinit var binding: ViewArticleItemBinding
        override fun bindView(itemView: View) {
            binding = ViewArticleItemBinding.bind(itemView)
            binding.categoryCarousel.apply {
                layoutManager = LinearLayoutManager(
                    binding.root.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = SimpleEpoxyController().adapter
                setPadding(insetOnlyCarouselPadding())
            }
        }
    }
}
