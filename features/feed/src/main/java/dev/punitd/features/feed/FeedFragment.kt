package dev.punitd.features.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import dev.punitd.base.android.extensions.launchAndRepeatWithLifeCycle
import dev.punitd.base.android.extensions.viewBinding
import dev.punitd.data.Article
import dev.punitd.data.Channel
import dev.punitd.features.ChannelsListController
import dev.punitd.features.ChannelsListViewState
import dev.punitd.features.feed.databinding.FragmentFeedBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FeedFragment :
    Fragment(),
    FeedController.AdapterCallbacks,
    ChannelsListController.AdapterCallbacks {

    companion object {
        const val PEEK_HEIGHT_RATIO = 0.70
    }

    private val binding by viewBinding(FragmentFeedBinding::inflate)
    private val viewModel: FeedViewModel by viewModels()
    private val controller = FeedController(this)

    private lateinit var channelsBottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private val channelListController = ChannelsListController(this)

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

            channelSelectBtn.setOnClickListener {
                channelsBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                viewModel.getChannels()
            }
        }

        setUpChannelDrawer()
    }

    private fun setUpChannelDrawer() {
        channelsBottomSheetBehavior =
            BottomSheetBehavior.from(binding.channelDrawer.channelSelectionBottomSheet)
        channelsBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        val peekHeight =
            (requireActivity().window.decorView.measuredHeight * PEEK_HEIGHT_RATIO).toInt()
        channelsBottomSheetBehavior.peekHeight = peekHeight

        binding.channelDrawer.channelsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = channelListController.adapter
        }
    }

    private fun observeViewStates() {
        launchAndRepeatWithLifeCycle {
            launch {
                viewModel.bind().collect { state ->
                    when (state) {
                        FeedViewState.Initial -> {
                            // Do nothing
                        }
                        FeedViewState.Loading -> {
                            binding.loader.visibility = View.VISIBLE
                            controller.setData(null)
                        }
                        is FeedViewState.Error -> {
                            binding.loader.visibility = View.GONE
                            controller.setData(null)
                        }
                        is FeedViewState.Success -> {
                            binding.loader.visibility = View.GONE
                            binding.channelSelectBtn.text = state.channel.title
                            controller.setData(state.channel)
                        }
                    }
                }
            }

            launch {
                viewModel.bindChannelsList().collect { state ->
                    when (state) {
                        ChannelsListViewState.Initial -> {
                            channelsBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                            binding.channelDrawer.intermediateLoader.visibility = View.VISIBLE
                        }
                        is ChannelsListViewState.Error -> {
                            channelsBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                            binding.channelDrawer.intermediateLoader.visibility = View.GONE
                        }
                        is ChannelsListViewState.Success -> {
                            if (!viewModel.isChannelSelected()) {
                                binding.channelDrawer.intermediateLoader.visibility = View.GONE
                                channelListController.setData(state.channels)
                                val channel = state.channels.first()
                                viewModel.fetchArticles(channel.link)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onArticleClicked(article: Article) {
        val bundle = bundleOf("Article" to article)
        findNavController().navigate(R.id.action_to_article_detail, bundle)
    }

    override fun onChannelSelected(channel: Channel) {
        viewModel.selectChannel(channel)
        viewModel.fetchArticles(channel.link)
        channelsBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        binding.channelSelectBtn.text = channel.title
    }
}
