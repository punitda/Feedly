package dev.punitd.features.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.punitd.base.android.extensions.launchAndRepeatWithLifeCycle
import dev.punitd.base.android.extensions.viewBinding
import dev.punitd.features.feed.databinding.FragmentFeedBinding
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private val binding by viewBinding(FragmentFeedBinding::inflate)
    private val viewModel: FeedViewModel by viewModels()
    private val controller = FeedController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchArticles("https://medium.com/feed/backchannel")
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
        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = controller.adapter
        }
    }

    private fun observeViewStates() {
        launchAndRepeatWithLifeCycle {
            viewModel.bind().collect { state ->
                when (state) {
                    is FeedViewState.Error -> {
                        binding.loader.visibility = View.GONE
                    }
                    FeedViewState.Loading -> {
                        binding.loader.visibility = View.VISIBLE
                    }
                    is FeedViewState.Success -> {
                        binding.loader.visibility = View.GONE
                        controller.setData(state.channel)
                    }
                }
            }
        }
    }
}
