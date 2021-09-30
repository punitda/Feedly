package dev.punitd.ui.components.view

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import dev.punitd.base.android.extensions.resolveColorAttr
import dev.punitd.data.Channel
import dev.punitd.ui.components.R
import dev.punitd.ui.components.databinding.ViewChannelSelectionItemBinding
import dev.punitd.ui.components.view.ChannelSelectionItemView.ChannelSelectionItemHolder

@EpoxyModelClass
abstract class ChannelSelectionItemView : EpoxyModelWithHolder<ChannelSelectionItemHolder>() {
    @EpoxyAttribute
    lateinit var channel: Channel

    @EpoxyAttribute(DoNotHash)
    lateinit var clickListener: View.OnClickListener

    override fun getDefaultLayout() = R.layout.view_channel_selection_item

    override fun bind(holder: ChannelSelectionItemHolder) {
        holder.binding.apply {
            channelName.text = channel.title
            if (channel.isSelected) {
                selectedIcon.visibility = View.VISIBLE
                channelName.setTextColor(root.context.resolveColorAttr(R.attr.colorOnPrimary))
                root.setBackgroundColor(root.context.resolveColorAttr(R.attr.colorPrimary))
            } else {
                selectedIcon.visibility = View.GONE
                channelName.setTextColor(root.context.resolveColorAttr(R.attr.colorOnSurface))
                root.setBackgroundColor(root.context.resolveColorAttr(R.attr.colorSurface))
            }
            root.setOnClickListener(clickListener)
        }
    }

    class ChannelSelectionItemHolder : EpoxyHolder() {
        lateinit var binding: ViewChannelSelectionItemBinding
        override fun bindView(itemView: View) {
            binding = ViewChannelSelectionItemBinding.bind(itemView)
        }
    }
}
