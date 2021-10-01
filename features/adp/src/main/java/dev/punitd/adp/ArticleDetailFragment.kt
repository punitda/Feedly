package dev.punitd.adp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.core.view.isGone
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import dev.punitd.adp.databinding.FragmentArticleDetailBinding
import dev.punitd.base.android.extensions.launchAndRepeatWithLifeCycle
import dev.punitd.base.android.extensions.viewBinding
import dev.punitd.data.Article
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ArticleDetailFragment : Fragment() {

    private val binding by viewBinding(FragmentArticleDetailBinding::inflate)
    private val viewModel: ArticleDetailViewModel by viewModels()
    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        article = arguments?.getParcelable("Article")
        viewModel.checkIfBookmarked(article?.guid ?: "")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        observeViewStates()
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

                toolbarLayout.title = it.title
            }

            articleDetailScrollView.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
                    val shouldShowCollapsedTitle = scrollY > toolbar.height
                    toolbarLayout.isTitleEnabled = shouldShowCollapsedTitle
                    bookmarkBtn.isGone = shouldShowCollapsedTitle
                }
            )

            toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

            bookmarkBtn.setOnClickListener {
                article?.let { viewModel.toggleBookmark(it) }
            }
        }
    }

    private fun observeViewStates() {
        launchAndRepeatWithLifeCycle {
            viewModel.bind().collect { isBookmarked ->
                binding.apply {
                    if (isBookmarked) {
                        bookmarkBtn.setImageDrawable(
                            ContextCompat.getDrawable(
                                bookmarkBtn.context,
                                R.drawable.ic_bookmarked
                            )
                        )
                    } else {
                        bookmarkBtn.setImageDrawable(
                            ContextCompat.getDrawable(
                                bookmarkBtn.context,
                                R.drawable.ic_bookmark
                            )
                        )
                    }
                }
            }
        }
    }
}
