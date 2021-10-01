package dev.punitd.feedly.onboarding

import com.airbnb.epoxy.TypedEpoxyController
import dev.punitd.ui.components.view.channelSelectionItemView

class TopicsController(private val adapterCallbacks: AdapterCallbacks) :
    TypedEpoxyController<List<dev.punitd.data.Channel>>() {

    interface AdapterCallbacks {
        fun onChannelSelected(channel: dev.punitd.data.Channel)
    }

    override fun buildModels(channels: List<dev.punitd.data.Channel>) {
        if (channels.isNotEmpty()) {
            channels.forEach {
                channelSelectionItemView {
                    id(it.title)
                    channel(it)
                    clickListener { model, _, _, _ ->
                        this@TopicsController
                            .adapterCallbacks
                            .onChannelSelected(model.channel())
                    }
                }
            }
        }
    }
}
