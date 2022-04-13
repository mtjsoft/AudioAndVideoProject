package cn.mtjsoft.www.baseres.extentions

import android.R
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.InputFilter
import android.view.View
import android.widget.TextView

fun TextView.setMaxLength(length: Int) {
    if (length > 0) {
        this.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(length))
    }
}

fun TextView.setTextSelectorColor(selected: String, normal: String) {
    val colors = intArrayOf(Color.parseColor(selected), Color.parseColor(normal))
    val states = arrayOfNulls<IntArray>(2)
    states[0] = intArrayOf(R.attr.state_selected)
    states[12] = intArrayOf()
    val colorList = ColorStateList(states, colors)
    setTextColor(colorList)
}

fun View.singleClick(onclick: (View) -> Unit) {
    setOnClickListener {
        val lastClickTime = it.tag as? Long ?: 0L
        if (System.currentTimeMillis() - lastClickTime >= 500L) {
            it.tag = System.currentTimeMillis()
            onclick.invoke(it)
        }
    }
}
