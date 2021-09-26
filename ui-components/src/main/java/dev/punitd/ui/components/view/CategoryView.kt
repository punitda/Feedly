package dev.punitd.ui.components.view

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import dev.punitd.ui.components.R
import dev.punitd.ui.components.databinding.ViewCategoryBinding
import dev.punitd.ui.components.view.CategoryView.CategoryHolder

@EpoxyModelClass
abstract class CategoryView : EpoxyModelWithHolder<CategoryHolder>() {
    @EpoxyAttribute
    lateinit var category: String

    override fun getDefaultLayout() = R.layout.view_category

    override fun bind(holder: CategoryHolder) {
        holder.binding.name.text = category
    }

    class CategoryHolder : EpoxyHolder() {
        lateinit var binding: ViewCategoryBinding
        override fun bindView(itemView: View) {
            binding = ViewCategoryBinding.bind(itemView)
        }
    }
}
