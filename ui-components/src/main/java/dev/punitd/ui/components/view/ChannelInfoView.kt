package dev.punitd.ui.components.view

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import dev.punitd.data.Channel
import dev.punitd.ui.components.R
import dev.punitd.ui.components.databinding.ViewChannelInfoBinding
import dev.punitd.ui.components.view.ChannelInfoView.ChannelInfoHolder

@EpoxyModelClass
abstract class ChannelInfoView : EpoxyModelWithHolder<ChannelInfoHolder>() {
    @EpoxyAttribute
    lateinit var channel: Channel

    override fun getDefaultLayout() = R.layout.view_channel_info

    override fun bind(holder: ChannelInfoHolder) {
        holder.binding.apply {
            channelTitle.text = channel.title
            channelDescription.text = channel.description
        }
    }

    class ChannelInfoHolder : EpoxyHolder() {
        lateinit var binding: ViewChannelInfoBinding
        override fun bindView(itemView: View) {
            binding = ViewChannelInfoBinding.bind(itemView)
        }
    }
}
