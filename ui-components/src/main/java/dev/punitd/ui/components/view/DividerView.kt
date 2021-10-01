package dev.punitd.ui.components.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import dev.punitd.ui.components.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class DividerView : LinearLayout {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attr: AttributeSet? = null) : super(context, attr) {
        init()
    }

    constructor(context: Context, attr: AttributeSet? = null, styleAttr: Int) :
        super(context, attr, styleAttr) {
            init()
        }

    private fun init() {
        setPaddingDp()
        setDividerColor()
    }

    @JvmOverloads
    @ModelProp
    fun setPaddingDp(@DimenRes paddingRes: Int = R.dimen.grid_2) {
        val px: Int = resToPx(paddingRes)
        setPadding(0, px / 2, 0, px / 2)
    }

    @JvmOverloads
    @ModelProp
    fun setDividerColor(@ColorRes color: Int = android.R.color.transparent) {
        setBackgroundColor(ContextCompat.getColor(context, color))
    }

    @Px
    private fun resToPx(@DimenRes itemSpacingRes: Int): Int {
        return resources.getDimensionPixelOffset(itemSpacingRes)
    }
}
