package dev.punitd.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.punitd.base.android.extensions.launchAndRepeatWithLifeCycle
import dev.punitd.base.android.extensions.viewBinding
import dev.punitd.bookmark.databinding.FragmentBookmarksBinding
import dev.punitd.data.Article
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class BookmarksFragment : Fragment(), BookmarksController.AdapterCallbacks {

    private val binding by viewBinding(FragmentBookmarksBinding::inflate)
    private val viewModel: BookmarksViewModel by viewModels()
    private val controller = BookmarksController(this)

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
            rv.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = controller.adapter
            }
        }
    }

    private fun observeViewStates() {
        launchAndRepeatWithLifeCycle {
            viewModel.subscribeToBookmarks().collect { articles ->
                controller.setData(articles)
                applySideEffect(articles)
            }
        }
    }

    private fun applySideEffect(articles: List<Article>) {
        binding.noBookmarksFound.isGone = articles.isNotEmpty()
    }

    override fun onArticleClicked(article: Article) {
        val bundle = bundleOf("Article" to article)
        findNavController().navigate(R.id.action_to_article_detail, bundle)
    }
}
