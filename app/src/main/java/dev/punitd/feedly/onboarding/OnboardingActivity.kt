package dev.punitd.feedly.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.punitd.base.android.extensions.viewBinding
import dev.punitd.data.Channel
import dev.punitd.feedly.MainActivity
import dev.punitd.feedly.databinding.ActivityOnboardingBinding
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@OptIn(InternalCoroutinesApi::class)
@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity(), TopicsController.AdapterCallbacks {

    private val binding by viewBinding(ActivityOnboardingBinding::inflate)
    private val viewModel: OnboardingViewModel by viewModels()
    private val controller = TopicsController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpUI()
        observeViewStates()
    }

    private fun setUpUI() {
        binding.fab.setOnClickListener {
            navigateToMainScreen()
        }

        binding.channelsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = controller.adapter
        }
    }

    private fun observeViewStates() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    val channels = viewModel.getSavedChannels()
                    if (channels.isEmpty()) {
                        showAllViews()
                        viewModel.getPrefilledChannels()
                    } else {
                        navigateToMainScreen()
                    }
                }
                launch {
                    viewModel.bindPreFilledChannels().collect { channels ->
                        controller.setData(channels)
                    }
                }
            }
        }
    }

    private fun navigateToMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showAllViews() {
        binding.apply {
            appIcon.visibility = View.VISIBLE
            appName.visibility = View.VISIBLE
            choose.visibility = View.VISIBLE
            channelsRv.visibility = View.VISIBLE
            fab.visibility = View.VISIBLE
            loader.visibility = View.GONE
        }
    }

    override fun onChannelSelected(channel: Channel) {
        viewModel.selectChannel(channel)
    }
}
