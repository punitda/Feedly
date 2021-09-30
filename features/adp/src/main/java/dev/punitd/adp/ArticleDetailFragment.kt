package dev.punitd.adp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import dev.punitd.adp.databinding.FragmentArticleDetailBinding
import dev.punitd.base.android.extensions.viewBinding
import dev.punitd.data.Article

@AndroidEntryPoint
class ArticleDetailFragment : Fragment() {

    private val binding by viewBinding(FragmentArticleDetailBinding::inflate)
    private val args: ArticleDetailFragmentArgs by navArgs()
    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        article = args.article
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    private fun setUpUI() {
        binding.apply {
            article?.let {

                detailImage.load(it.imageUrl) {
                    placeholder(R.drawable.image_place_holder)
                    error(R.drawable.image_place_holder)
                }

                articleTitle.text = it.title
                articleAuthor.text = it.author
                articePubDate.text = it.pubDate

                articleContent.text = HtmlCompat.fromHtml(it.content, FROM_HTML_MODE_LEGACY)
            }
        }
    }
}
