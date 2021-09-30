package dev.punitd.features

import com.airbnb.epoxy.TypedEpoxyController
import dev.punitd.data.Channel
import dev.punitd.features.feed.R
import dev.punitd.ui.components.view.channelSelectionItemView
import dev.punitd.ui.components.view.sectionHeader

class ChannelsListController(
    private val adapterCallbacks: AdapterCallbacks
) : TypedEpoxyController<List<Channel>>() {

    interface AdapterCallbacks {
        fun onChannelSelected(channel: Channel)
    }

    override fun buildModels(channels: List<Channel>) {
        if (channels.isNotEmpty()) {
            sectionHeader {
                id("SelectChannelHeader")
                titleRes(R.string.channel_selection_section_header)
            }

            channels.forEach {
                channelSelectionItemView {
                    id(it.title)
                    channel(it)
                    clickListener { model, _, _, _ ->
                        this@ChannelsListController
                            .adapterCallbacks
                            .onChannelSelected(model.channel())
                    }
                }
            }
        }
    }
}
