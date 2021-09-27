package dev.punitd.ui.components.view

import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StringRes
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import dev.punitd.base.android.extensions.resolveColorAttr
import dev.punitd.ui.components.R
import dev.punitd.ui.components.databinding.ViewSectionHeaderBinding
import dev.punitd.ui.components.view.SectionHeader.SectionHeaderHolder

@EpoxyModelClass
abstract class SectionHeader : EpoxyModelWithHolder<SectionHeaderHolder>() {

    @EpoxyAttribute
    @StringRes
    var titleRes: Int = R.string.placeholder_section_header

    @EpoxyAttribute
    var title: String? = null

    @EpoxyAttribute
    @AttrRes
    var textColor: Int = R.attr.colorOnBackground

    override fun getDefaultLayout() = R.layout.view_section_header

    override fun bind(holder: SectionHeaderHolder) {
        holder.binding.apply {
            if (title != null)
                header.text = title
            else
                header.setText(titleRes)
            header.setTextColor(root.context.resolveColorAttr(textColor))
        }
    }

    class SectionHeaderHolder : EpoxyHolder() {
        lateinit var binding: ViewSectionHeaderBinding
        override fun bindView(itemView: View) {
            binding = ViewSectionHeaderBinding.bind(itemView)
        }
    }
}
